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

/**
 * Implementation of the RabbitMQ client for communication with RabbitMQ message brokers.
 * Provides methods for processing stream and reply requests over the RabbitMQ protocol.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
public class RabbitMQClient extends AbstractRabbitMQRequestHandler {

    /**
     * Constructs a new RabbitMQClient with the specified channel handler.
     *
     * @param client the channel handler to use for communication
     */
    public RabbitMQClient(ChannelHandler client) {
        super(client);
    }

    /**
     * Processes a stream request from the specified queue.
     * This implementation returns null as it's not implemented.
     *
     * @param fromTopic the queue from which the request was received
     * @param data the request data as bytes
     * @return null as this functionality is not implemented
     */
    @Override
    public String processStreamRequest(String fromTopic, byte[] data) {
        return null;
    }

    /**
     * Processes a reply request from the specified queue.
     * This implementation returns null as it's not implemented.
     *
     * @param fromTopic the queue from which the request was received
     * @param data the request data as bytes
     * @return null as this functionality is not implemented
     */
    @Override
    public String processReplyRequest(String fromTopic, byte[] data) {
        return null;
    }
}
