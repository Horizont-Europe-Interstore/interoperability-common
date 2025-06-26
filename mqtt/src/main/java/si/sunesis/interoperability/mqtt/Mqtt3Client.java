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
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

/**
 * Implementation of MQTT 3.1.1 client for handling MQTT communications.
 * Extends the abstract MQTT 3.1.1 request handler to provide concrete functionality.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public class Mqtt3Client extends AbstractMqtt3RequestHandler {

    /**
     * Constructs a new MQTT 3.1.1 client with the specified async client.
     *
     * @param client the MQTT 3.1.1 async client to use for communications
     */
    public Mqtt3Client(MqttAsyncClient client) {
        super(client);
    }

    /**
     * Processes a reply request from the specified topic.
     * This implementation simply returns a constant string.
     *
     * @param fromTopic the topic from which the request was received
     * @param data the request data as bytes
     * @return a constant string "processReplyRequest"
     */
    @Override
    public String processReplyRequest(String fromTopic, byte[] data) {
        return "processReplyRequest";
    }

    /**
     * Processes a stream request from the specified topic.
     * This implementation simply returns a constant string.
     *
     * @param fromTopic the topic from which the request was received
     * @param data the request data as bytes
     * @return a constant string "processStreamRequest"
     */
    @Override
    public String processStreamRequest(String fromTopic, byte[] data) {
        return "processStreamRequest";
    }
}
