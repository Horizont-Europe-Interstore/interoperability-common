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

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttSubscription;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.interfaces.RequestHandler;
import si.sunesis.interoperability.common.models.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Abstract base class for MQTT 5.0 request handlers.
 * Provides implementation of the request-response and publish-subscribe patterns for MQTT 5.0.
 * Extends the common AbstractRequestHandler and implements RequestHandler interface for MQTT 5.0 protocol.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractMqtt5RequestHandler extends AbstractRequestHandler<MqttAsyncClient, org.eclipse.paho.mqttv5.common.MqttMessage> implements RequestHandler<String, byte[]> {

    HashMap<String, Callback<byte[]>> callbacks = new HashMap<>();

    /**
     * Constructs a new AbstractMqtt5RequestHandler with the specified MQTT 5.0 async client.
     *
     * @param client the MQTT 5.0 async client to use for communication
     */
    protected AbstractMqtt5RequestHandler(MqttAsyncClient client) {
        this.client = client;
    }

    /**
     * Gets the MQTT 5.0 async client used by this handler.
     *
     * @return the MQTT 5.0 async client
     */
    @Override
    public MqttAsyncClient getClient() {
        return this.client;
    }

    /**
     * Disconnects the MQTT 5.0 client if it is connected.
     * Safely closes the connection to the MQTT broker.
     */
    @Override
    public void disconnect() {
        if (client.isConnected()) {
            log.debug("Disconnecting MQTT client");
            try {
                client.disconnect();
            } catch (MqttException e) {
                log.error("Failed to disconnect MQTT client", e);
            }
        }
    }

    /**
     * Publishes data to a specific subject/topic.
     * The data is published as-is to the specified MQTT topic.
     *
     * @param data    the data to publish
     * @param subject the MQTT topic to publish to
     */
    @Override
    public void publish(String data, String subject) {
        log.debug("Publishing message: {} to topic: {}", data, subject);

        if (data == null || data.isEmpty()) {
            log.error("Data is empty, topic: {}", subject);
            return;
        }

        try {
            client.publish(subject, new org.eclipse.paho.mqttv5.common.MqttMessage(data.getBytes()));
            log.debug("Published message: {} to topic: {}", data, subject);
        } catch (MqttException e) {
            log.error("Failed to publish message: {} to topic: {}", data, subject, e);
        }
    }

    /**
     * Subscribes to an MQTT topic to receive messages.
     * When messages are received, they are processed based on their content and type.
     *
     * @param subject  the MQTT topic to subscribe to
     * @param callback the callback to handle received messages
     */
    @Override
    public void subscribe(String subject, Callback<byte[]> callback) {
        log.debug("Subscribing to topic: {}", subject);

        try {
            client.subscribe(new MqttSubscription(subject, 0), null, null, (s, receivedMessage) -> {
                MqttMessage mqttMessage = MqttMessage.fromJson(receivedMessage.getPayload());

                if (mqttMessage.getReplyTo() != null) {
                    if (mqttMessage.getDuration() != null) {
                        handleStream(s, receivedMessage);
                        callback.onNext("Handled stream".getBytes(StandardCharsets.UTF_8));
                    } else {
                        handleRequestReply(s, receivedMessage);
                        callback.onNext("Handled request".getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    callback.onNext(mqttMessage.getContent().getBytes());
                }
            }, getProps());

            log.debug("Subscribed to topic: {}", subject);
        } catch (MqttException e) {
            log.error("Failed to subscribe to topic: {}", subject, e);
        }
    }

    /**
     * Sends a request and establishes a stream of responses.
     * Subscribes to the reply topic and then publishes a message with the request data.
     *
     * @param data     the request data
     * @param subject  the MQTT topic to send the request to
     * @param replyTo  the MQTT topic where responses should be sent
     * @param duration the duration for which to maintain the stream
     * @param callback the callback to handle received responses
     */
    @Override
    public void requestStream(String data, String subject, String replyTo, Duration duration, Callback<byte[]> callback) {
        try {
            client.subscribe(new MqttSubscription(replyTo, 0), null, null, (s, receivedMessage) -> {
                MqttMessage mqttMessage = MqttMessage.fromJson(receivedMessage.getPayload());
                callback.onNext(mqttMessage.getContent().getBytes());
            }, getProps());

            log.debug("Subscribed to stream topic: {}", subject);

            MqttMessage mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                    .content(data)
                    .replyTo(replyTo)
                    .duration(duration)
                    .build();

            publish(mqttMessage.toJsonString(), subject);
        } catch (MqttException e) {
            log.error("Failed to subscribe to stream topic: {}", replyTo, e);
        }
    }

    /**
     * Sends a request that can receive multiple responses on a specified reply topic.
     * Subscribes to the reply topic and then publishes a message with the request data.
     *
     * @param data     the request data
     * @param subject  the MQTT topic to send the request to
     * @param replyTo  the MQTT topic where responses should be sent
     * @param callback the callback to handle received responses
     */
    @Override
    public void requestReplyToMultiple(String data, String subject, String replyTo, Callback<byte[]> callback) {
        try {
            client.subscribe(new MqttSubscription(replyTo, 0), null, null, (s, receivedMessage) -> {
                MqttMessage mqttMessage = MqttMessage.fromJson(receivedMessage.getPayload());
                callback.onNext(mqttMessage.getContent().getBytes());
            }, getProps());

            log.debug("Subscribed to reply to topic: {}", subject);

            MqttMessage mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                    .content(data)
                    .replyTo(replyTo)
                    .build();

            publish(mqttMessage.toJsonString(), subject);
        } catch (MqttException e) {
            log.error("Failed to subscribe to reply to topic: {}", replyTo, e);
        }
    }

    /**
     * Sends a request and expects a single reply.
     * Creates a unique reply topic and uses requestReplyToMultiple to handle the request-reply pattern.
     *
     * @param data     the request data
     * @param subject  the MQTT topic to send the request to
     * @param callback the callback to handle the response
     */
    @Override
    public void requestReply(String data, String subject, Callback<byte[]> callback) {
        String replyTopic = subject + System.currentTimeMillis() + client.getClientId();

        requestReplyToMultiple(data, subject, replyTopic, callback);
    }

    /**
     * Handles a request-reply pattern for MQTT 5.0 messages.
     * Processes the received message and sends a reply to the specified reply topic.
     *
     * @param subject      the MQTT topic from which the request was received
     * @param mqtt5Publish the received MQTT 5.0 publish message
     */
    @Override
    protected void handleRequestReply(String subject, org.eclipse.paho.mqttv5.common.MqttMessage mqtt5Publish) {
        MqttMessage mqttMessage = MqttMessage.fromJson(mqtt5Publish.getPayload());

        String replyTo = mqttMessage.getReplyTo();
        String reply = processReplyRequest(subject, mqttMessage.getContent().getBytes());

        mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                .content(reply)
                .build();

        publish(mqttMessage.toJsonString(), replyTo);
    }

    /**
     * Handles a stream communication pattern for MQTT 5.0 messages.
     * Processes the received message and sends multiple replies at a regular interval.
     *
     * @param subject      the MQTT topic from which the request was received
     * @param mqtt5Publish the received MQTT 5.0 publish message
     * @throws IllegalStateException if the Duration header is missing in the message
     */
    @Override
    protected void handleStream(String subject, org.eclipse.paho.mqttv5.common.MqttMessage mqtt5Publish) {
        MqttMessage mqttMessage = MqttMessage.fromJson(mqtt5Publish.getPayload());
        if (mqttMessage == null || mqttMessage.getDuration() == null) {
            throw new IllegalStateException("Duration header is missing");
        }

        String replyTo = mqttMessage.getReplyTo();
        String content = mqttMessage.getContent();

        Duration duration = mqttMessage.getDuration();

        int numOfMessages = Math.toIntExact(duration.toSeconds());
        for (int iii = 0; iii < numOfMessages; iii++) {
            log.debug("Sending message {} of {}", iii + 1, numOfMessages);

            String reply = processStreamRequest(subject, content.getBytes());

            mqttMessage = MqttMessage.MqttMessageBuilder.builder()
                    .content(reply)
                    .build();


            publish(mqttMessage.toJsonString(), replyTo);

            log.debug("Sent message {} of {}", iii + 1, numOfMessages);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("Error while sleeping", e);
                Thread.currentThread().interrupt();
            }
        }

        log.debug("Finished sending {} messages", numOfMessages);
    }

    /**
     * Generates MQTT properties for subscription identifiers.
     *
     * @return MqttProperties with a random subscription identifier.
     */
    private MqttProperties getProps() {
        // Create subscription properties, to fix bug in paho library
        final MqttProperties props = new MqttProperties();
        SecureRandom r = new SecureRandom();
        int randomInt = r.nextInt(268435454);
        if (randomInt == 0) {
            randomInt = 1; // Ensure subscription identifier is not zero
        }
        props.setSubscriptionIdentifiers(List.of(randomInt));

        return props;
    }
}

