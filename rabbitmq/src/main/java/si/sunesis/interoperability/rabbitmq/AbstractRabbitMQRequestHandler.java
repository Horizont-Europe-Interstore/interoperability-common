/*
 *  Copyright (c) 2023-2024 Sunesis and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package si.sunesis.interoperability.rabbitmq;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.exceptions.HandlerException;
import si.sunesis.interoperability.common.interfaces.RequestHandler;
import si.sunesis.interoperability.common.models.MqttMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Abstract base class for RabbitMQ request handlers.
 * Provides implementation of the request-response and publish-subscribe patterns for RabbitMQ.
 * Extends the common AbstractRequestHandler and implements RequestHandler interface for RabbitMQ protocol.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractRabbitMQRequestHandler extends AbstractRequestHandler<ChannelHandler, Delivery> implements RequestHandler<String, byte[]> {

    /**
     * Constructs a new AbstractRabbitMQRequestHandler with the specified channel handler.
     *
     * @param client the channel handler to use for communication
     */
    protected AbstractRabbitMQRequestHandler(ChannelHandler client) {
        this.client = client;
    }

    /**
     * Gets the channel handler used by this handler.
     *
     * @return the channel handler
     */
    @Override
    public ChannelHandler getClient() {
        return this.client;
    }

    /**
     * Publishes data to a specific queue.
     * Delegates to the channel handler for the actual publishing.
     *
     * @param data the data to publish
     * @param queueName the queue to publish to
     * @throws HandlerException if an error occurs during publishing
     */
    @Override
    public void publish(String data, String queueName) throws HandlerException {
        log.debug("Publishing message: {} to queue: {}", data, queueName);

        client.publish(data, queueName);
    }

    /**
     * Subscribes to a queue to receive messages.
     * When messages are received, they are processed based on their content and type.
     *
     * @param queueName the queue to subscribe to
     * @param callback the callback to handle received messages
     */
    @Override
    public void subscribe(String queueName, Callback<byte[]> callback) {
        log.debug("Subscribing to queue: {}", queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            MqttMessage mqttMessage = MqttMessage.fromJson(delivery.getBody());

            if (mqttMessage.getReplyTo() != null) {
                if (mqttMessage.getDuration() != null) {
                    handleStream(queueName, delivery);
                    callback.onNext("Handled stream".getBytes(StandardCharsets.UTF_8));
                } else {
                    handleRequestReply(queueName, delivery);
                    callback.onNext("Handled request".getBytes(StandardCharsets.UTF_8));
                }
            } else {
                callback.onNext(mqttMessage.getContent().getBytes());
            }
        };

        try {
            client.subscribe(queueName, deliverCallback);
        } catch (HandlerException e) {
            log.error("Error while subscribing to queue", e);
        }
    }

    /**
     * Sends a request and establishes a stream of responses.
     * Subscribes to the reply queue and then publishes a message with the request data.
     * Duration is included in the message to indicate the length of the stream.
     *
     * @param data the request data
     * @param queueName the queue to send the request to
     * @param replyTo the queue where responses should be sent
     * @param duration the duration for which to maintain the stream
     * @param callback the callback to handle received responses
     * @throws HandlerException if an error occurs during the request
     */
    @Override
    public void requestStream(String data, String queueName, String replyTo, Duration duration, Callback<byte[]> callback) throws HandlerException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            MqttMessage mqttMessage = MqttMessage.fromJson(delivery.getBody());

            callback.onNext(mqttMessage.getContent().getBytes());
        };

        client.subscribe(replyTo, deliverCallback);

        MqttMessage mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                .content(data)
                .replyTo(replyTo)
                .duration(duration)
                .build();

        client.publish(mqttMessage.toJsonString(), queueName);
    }

    /**
     * Sends a request that can receive multiple responses on a specified reply queue.
     * Subscribes to the reply queue and then publishes a message with the request data.
     *
     * @param data the request data
     * @param queueName the queue to send the request to
     * @param replyTo the queue where responses should be sent
     * @param callback the callback to handle received responses
     * @throws HandlerException if an error occurs during the request
     */
    @Override
    public void requestReplyToMultiple(String data, String queueName, String replyTo, Callback<byte[]> callback) throws HandlerException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            MqttMessage mqttMessage = MqttMessage.fromJson(delivery.getBody());

            callback.onNext(mqttMessage.getContent().getBytes());
        };

        client.subscribe(replyTo, deliverCallback);

        MqttMessage mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                .content(data)
                .replyTo(replyTo)
                .build();

        client.publish(mqttMessage.toJsonString(), queueName);
    }

    /**
     * Sends a request and expects a single reply.
     * Creates a unique reply queue name and uses requestReplyToMultiple to handle the request-reply pattern.
     *
     * @param data the request data
     * @param queueName the queue to send the request to
     * @param callback the callback to handle the response
     * @throws HandlerException if an error occurs during the request
     */
    @Override
    public void requestReply(String data, String queueName, Callback<byte[]> callback) throws HandlerException {
        String replyTopic = queueName + System.currentTimeMillis() + client.getChannel().getChannelNumber();

        requestReplyToMultiple(data, queueName, replyTopic, callback);
    }

    /**
     * Handles a request-reply pattern for RabbitMQ messages.
     * Processes the received message and sends a reply to the specified reply queue.
     *
     * @param queueName the queue from which the request was received
     * @param message the received RabbitMQ delivery
     */
    @Override
    protected void handleRequestReply(String queueName, Delivery message) {
        MqttMessage mqttMessage = MqttMessage.fromJson(message.getBody());

        String replyTo = mqttMessage.getReplyTo();
        String reply = processReplyRequest(queueName, mqttMessage.getContent().getBytes());

        mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                .content(reply)
                .build();

        try {
            client.publish(mqttMessage.toJsonString(), replyTo);
        } catch (HandlerException e) {
            log.error("Error while publishing reply", e);
        }
    }

    /**
     * Handles a stream communication pattern for RabbitMQ messages.
     * Processes the received message and sends multiple replies at a regular interval.
     *
     * @param queueName the queue from which the request was received
     * @param message the received RabbitMQ delivery
     * @throws IllegalStateException if the Duration header is missing in the message
     */
    @Override
    protected void handleStream(String queueName, Delivery message) {
        MqttMessage mqttMessage = MqttMessage.fromJson(message.getBody());
        if (mqttMessage == null || mqttMessage.getDuration() == null) {
            throw new IllegalStateException("Duration header is missing");
        }

        String replyTo = mqttMessage.getReplyTo();
        String content = mqttMessage.getContent();

        Duration duration = mqttMessage.getDuration();

        int numOfMessages = Math.toIntExact(duration.toSeconds());
        for (int iii = 0; iii < numOfMessages; iii++) {
            try {
                log.debug("Sending message {} of {}", iii + 1, numOfMessages);

                String reply = processStreamRequest(queueName, content.getBytes());

                mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                        .content(reply)
                        .build();

                client.publish(mqttMessage.toJsonString(), replyTo);

                log.debug("Sent message {} of {}", iii + 1, numOfMessages);

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("Error while sleeping", e);
                Thread.currentThread().interrupt();
            } catch (HandlerException e) {
                log.error("Error while publishing stream", e);
            }
        }

        log.debug("Finished sending {} messages", numOfMessages);
    }

    /**
     * Disconnects the RabbitMQ client if it is connected.
     * Safely closes the channel and connection to the RabbitMQ broker.
     */
    @Override
    public void disconnect() {
        if (client != null) {
            try {
                client.getChannel().close();
                client.getConnection().close();
            } catch (IOException | TimeoutException e) {
                log.error("Error while closing MQ connection", e);
            }
        }
    }
}
