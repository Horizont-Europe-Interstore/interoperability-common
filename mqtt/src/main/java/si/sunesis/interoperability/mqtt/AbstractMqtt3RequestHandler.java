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
package si.sunesis.interoperability.mqtt;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.subscribe.suback.Mqtt3SubAck;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.constants.Constants;
import si.sunesis.interoperability.common.interfaces.RequestHandler;
import si.sunesis.interoperability.common.models.MqttMessage;

import javax.json.Json;
import javax.json.JsonObject;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Abstract base class for MQTT 3.1.1 request handlers.
 * Provides implementation of the request-response and publish-subscribe patterns for MQTT 3.1.1.
 * Extends the common AbstractRequestHandler and implements RequestHandler interface for MQTT 3.1.1 protocol.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractMqtt3RequestHandler extends AbstractRequestHandler<Mqtt3AsyncClient, Mqtt3Publish> implements RequestHandler<String, byte[]> {

    /**
     * Constructs a new AbstractMqtt3RequestHandler with the specified MQTT 3.1.1 async client.
     *
     * @param client the MQTT 3.1.1 async client to use for communication
     */
    protected AbstractMqtt3RequestHandler(Mqtt3AsyncClient client) {
        this.client = client;
    }

    /**
     * Gets the MQTT 3.1.1 async client used by this handler.
     *
     * @return the MQTT 3.1.1 async client
     */
    @Override
    public Mqtt3AsyncClient getClient() {
        return this.client;
    }

    /**
     * Disconnects the MQTT 3.1.1 client if it is connected.
     * Safely closes the connection to the MQTT broker.
     */
    @Override
    public void disconnect() {
        if (client.getState().isConnected()) {
            log.debug("Disconnecting MQTT client");
            client.disconnect();
        }
    }

    /**
     * Publishes data to a specific subject/topic.
     * The data is published as-is to the specified MQTT topic.
     *
     * @param data the data to publish
     * @param subject the MQTT topic to publish to
     */
    @Override
    public void publish(String data, String subject) {
        log.debug("Publishing message: {} to topic: {}", data, subject);

        if (data == null || data.isEmpty()) {
            log.error("Data is empty, topic: {}", subject);
            return;
        }

        Mqtt3Publish publish = client.publishWith()
                .topic(subject)
                .payload(data.getBytes())
                .send().join();

        log.debug("Published message: {}", publish);
    }

    /**
     * Subscribes to an MQTT topic to receive messages.
     * When messages are received, they are processed based on their content and type.
     *
     * @param subject the MQTT topic to subscribe to
     * @param callback the callback to handle received messages
     */
    @Override
    public void subscribe(String subject, Callback<byte[]> callback) {
        log.debug("Subscribing to topic: {}", subject);

        Mqtt3SubAck ack = client.subscribeWith()
                .topicFilter(subject)
                .callback(message -> {
                    MqttMessage mqttMessage = MqttMessage.fromJson(message.getPayloadAsBytes());

                    if (mqttMessage.getReplyTo() != null) {
                        if (mqttMessage.getDuration() != null) {
                            handleStream(subject, message);
                            callback.onNext("Handled stream".getBytes(StandardCharsets.UTF_8));
                        } else {
                            handleRequestReply(subject, message);
                            callback.onNext("Handled request".getBytes(StandardCharsets.UTF_8));
                        }
                    } else {
                        callback.onNext(mqttMessage.getContent().getBytes());
                    }
                }).send().join();

        log.debug("Subscribed to topic: {}", ack);
    }

    /**
     * Sends a request and establishes a stream of responses.
     * Subscribes to the reply topic and then publishes a message with the request data.
     *
     * @param data the request data
     * @param subject the MQTT topic to send the request to
     * @param replyTo the MQTT topic where responses should be sent
     * @param duration the duration for which to maintain the stream
     * @param callback the callback to handle received responses
     */
    @Override
    public void requestStream(String data, String subject, String replyTo, Duration duration, Callback<byte[]> callback) {
        Mqtt3SubAck ack = client.subscribeWith()
                .topicFilter(replyTo)
                .qos(MqttQos.EXACTLY_ONCE)
                .callback(message -> {
                    MqttMessage mqttMessage = MqttMessage.fromJson(message.getPayloadAsBytes());
                    callback.onNext(mqttMessage.getContent().getBytes());
                }).send().join();

        log.debug("Subscribed to stream topic: {}", ack);

        JsonObject json = Json.createObjectBuilder()
                .add(Constants.DURATION, duration.toMillis())
                .add(Constants.CONTENT, data)
                .add(Constants.REPLY_TO, replyTo)
                .build();

        Mqtt3Publish result = client.publishWith()
                .topic(subject)
                .payload(json.toString().getBytes())
                .send().join();

        log.debug("Published stream message: {}", result);
    }

    /**
     * Sends a request that can receive multiple responses on a specified reply topic.
     * Subscribes to the reply topic and then publishes a message with the request data.
     *
     * @param data the request data
     * @param subject the MQTT topic to send the request to
     * @param replyTo the MQTT topic where responses should be sent
     * @param callback the callback to handle received responses
     */
    @Override
    public void requestReplyToMultiple(String data, String subject, String replyTo, Callback<byte[]> callback) {
        Mqtt3SubAck ack = client.subscribeWith()
                .topicFilter(replyTo)
                .callback(message -> {
                    MqttMessage mqttMessage = MqttMessage.fromJson(message.getPayloadAsBytes());
                    callback.onNext(mqttMessage.getContent().getBytes());
                }).send().join();

        log.debug("Subscribed to reply topic: {}", ack);

        JsonObject json = Json.createObjectBuilder()
                .add(Constants.REPLY_TO, replyTo)
                .add(Constants.CONTENT, data)
                .build();

        Mqtt3Publish result = client.publishWith()
                .topic(subject)
                .payload(json.toString().getBytes())
                .send().join();

        log.debug("Published reply message: {}", result);
    }

    /**
     * Sends a request and expects a single reply.
     * Creates a unique reply topic and uses requestReplyToMultiple to handle the request-reply pattern.
     *
     * @param data the request data
     * @param subject the MQTT topic to send the request to
     * @param callback the callback to handle the response
     */
    @Override
    public void requestReply(String data, String subject, Callback<byte[]> callback) {
        String replyTopic = subject + System.currentTimeMillis() + client.getConfig().getClientIdentifier().orElse(null);

        requestReplyToMultiple(data, subject, replyTopic, callback);
    }

    /**
     * Handles a request-reply pattern for MQTT 3.1.1 messages.
     * Processes the received message and sends a reply to the specified reply topic.
     *
     * @param subject the MQTT topic from which the request was received
     * @param mqtt3Publish the received MQTT 3.1.1 publish message
     */
    @Override
    protected void handleRequestReply(String subject, Mqtt3Publish mqtt3Publish) {
        MqttMessage mqttMessage = MqttMessage.fromJson(mqtt3Publish.getPayloadAsBytes());

        String replyTo = mqttMessage.getReplyTo();
        String reply = processReplyRequest(subject, mqttMessage.getContent().getBytes());

        mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                .content(reply)
                .build();

        Mqtt3Publish publish = Mqtt3Publish.builder()
                .topic(replyTo)
                .payload(mqttMessage.toJsonString().getBytes())
                .build();

        client.publish(publish).join();
    }

    /**
     * Handles a stream communication pattern for MQTT 3.1.1 messages.
     * Processes the received message and sends multiple replies at a regular interval.
     *
     * @param subject the MQTT topic from which the request was received
     * @param mqtt3Publish the received MQTT 3.1.1 publish message
     * @throws IllegalStateException if the Duration header is missing in the message
     */
    @Override
    protected void handleStream(String subject, Mqtt3Publish mqtt3Publish) {
        MqttMessage mqttMessage = MqttMessage.fromJson(mqtt3Publish.getPayloadAsBytes());
        if (mqttMessage == null || mqttMessage.getDuration() == null) {
            throw new IllegalStateException("Duration header is missing");
        }

        String replyTo = mqttMessage.getReplyTo();
        String content = mqttMessage.getContent();

        Duration duration = mqttMessage.getDuration();

        int numOfMessages = Math.toIntExact(duration.toSeconds());
        for (int iii = 0; iii < numOfMessages; iii++) {
            String reply = processStreamRequest(subject, content.getBytes());

            mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                    .content(reply)
                    .build();

            Mqtt3Publish publish = Mqtt3Publish.builder()
                    .topic(replyTo)
                    .payload(mqttMessage.toJsonString().getBytes())
                    .build();

            client.publish(publish).join();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("Error while sleeping", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}

