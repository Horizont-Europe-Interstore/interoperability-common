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
package si.sunesis.interoperability.common.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.constants.Constants;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParsingException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Data
@Slf4j
public class MqttMessage {
    private String content;
    private String replyTo;
    private Duration duration;

    /**
     * Private constructor used by the builder.
     *
     * @param content the message content
     * @param replyTo the reply-to topic
     * @param duration the duration for stream requests
     */
    private MqttMessage(String content, String replyTo, Duration duration) {
        this.content = content;
        this.replyTo = replyTo;
        this.duration = duration;
    }

    /**
     * Creates an MqttMessage from a JSON string.
     *
     * @param jsonString the JSON string to parse
     * @return the created MqttMessage
     */
    public static MqttMessage fromJson(String jsonString) {
        return fromJson(jsonString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Creates an MqttMessage from JSON bytes.
     *
     * @param jsonBytes the JSON bytes to parse
     * @return the created MqttMessage
     */
    public static MqttMessage fromJson(byte[] jsonBytes) {
        JsonObject json;

        try {
            JsonObject jsonObject = Json.createReader(new ByteArrayInputStream(jsonBytes)).readObject();

            if (!jsonObject.containsKey(Constants.CONTENT)) {
                json = Json.createObjectBuilder()
                        .add(Constants.CONTENT, jsonObject.toString())
                        .build();
            } else {
                json = jsonObject;
            }

        } catch (JsonParsingException e) {
            String input = new String(jsonBytes, StandardCharsets.UTF_8);

            json = Json.createObjectBuilder()
                    .add(Constants.CONTENT, input)
                    .build();
        } catch (Exception e) {
            log.error("Error parsing JSON", e);
            throw e;
        }

        return MqttMessageBuilder.builder()
                .content(json.getString(Constants.CONTENT, null))
                .replyTo(json.getString(Constants.REPLY_TO, null))
                .duration(Duration.ofMillis(json.getInt(Constants.DURATION, 0)))
                .build();
    }

    /**
     * Converts this MqttMessage to a JSON string representation.
     *
     * @return the JSON string
     */
    public String toJsonString() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (content != null) {
            builder.add(Constants.CONTENT, content);
        }

        if (replyTo != null) {
            builder.add(Constants.REPLY_TO, replyTo);
        }

        if (duration != null) {
            builder.add(Constants.DURATION, duration.toMillis());
        }

        JsonObject jsonObject = builder.build();

        return jsonObject.toString();
    }

    /**
     * Builder class for creating MqttMessage instances.
     */
    public static class MqttMessageBuilder {
        private String content;
        private String replyTo;
        private Duration duration;

        /**
         * Creates a new MqttMessageBuilder instance.
         *
         * @return a new builder instance
         */
        public static MqttMessageBuilder builder() {
            return new MqttMessageBuilder();
        }

        /**
         * Sets the content of the message.
         *
         * @param content the content to set
         * @return this builder instance
         */
        public MqttMessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        /**
         * Sets the reply-to topic.
         *
         * @param replyTo the reply-to topic
         * @return this builder instance
         */
        public MqttMessageBuilder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * Sets the duration for stream requests.
         *
         * @param duration the duration to set
         * @return this builder instance
         */
        public MqttMessageBuilder duration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Builds an MqttMessage with the configured values.
         *
         * @return the built MqttMessage
         */
        public MqttMessage build() {
            return new MqttMessage(content, replyTo, duration);
        }
    }
}
