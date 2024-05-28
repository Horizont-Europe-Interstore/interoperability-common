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
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractRabbitMQRequestHandler extends AbstractRequestHandler<ChannelHandler, Delivery> implements RequestHandler<String, byte[]> {

    protected AbstractRabbitMQRequestHandler(ChannelHandler client) {
        this.client = client;
    }

    @Override
    public ChannelHandler getClient() {
        return this.client;
    }

    @Override
    public void publish(String data, String queueName) throws HandlerException {
        client.publish(data, queueName);
    }

    @Override
    public void subscribe(String queueName, Callback<byte[]> callback) {
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
        } catch (HandlerException ignored) {
        }
    }

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

    @Override
    public void requestReply(String data, String queueName, Callback<byte[]> callback) throws HandlerException {
        String replyTopic = queueName + System.currentTimeMillis() + client.getChannel().getChannelNumber();

        requestReplyToMultiple(data, queueName, replyTopic, callback);
    }

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
                throw new IllegalStateException(e.getMessage());
            } catch (HandlerException e) {
                log.error("Error while publishing stream", e);
            }
        }

        log.debug("Finished sending {} messages", numOfMessages);
    }

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
