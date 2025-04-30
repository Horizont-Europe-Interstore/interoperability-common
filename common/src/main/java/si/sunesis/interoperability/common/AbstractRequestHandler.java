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
package si.sunesis.interoperability.common;

/**
 * Abstract base class for request handlers that implements common functionality.
 *
 * @param <E> The type of client used by the handler
 * @param <M> The type of messages handled by the implementation
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
public abstract class AbstractRequestHandler<E, M> {

    /**
     * The client instance used for communication.
     */
    protected E client;

    /**
     * Gets the client instance used by this handler.
     *
     * @return the client instance
     */
    public abstract E getClient();

    /**
     * Processes a stream request received from a topic.
     *
     * @param fromTopic the topic from which the request was received
     * @param data the raw request data as bytes
     * @return the processed result or null if not handled
     */
    public String processStreamRequest(String fromTopic, byte[] data) {
        return null;
    }

    /**
     * Processes a reply request received from a topic.
     *
     * @param fromTopic the topic from which the request was received
     * @param data the raw request data as bytes
     * @return the processed result or null if not handled
     */
    public String processReplyRequest(String fromTopic, byte[] data) {
        return null;
    }

    /**
     * Handles a request-reply pattern.
     *
     * @param subject the subject/topic for the request
     * @param message the message to be handled
     */
    protected void handleRequestReply(String subject, M message) {
        // Modbus does not support this pattern
    }

    /**
     * Handles a stream communication pattern.
     *
     * @param subject the subject/topic for the stream
     * @param message the message to be streamed
     */
    protected void handleStream(String subject, M message) {
        // Modbus does not support this pattern
    }
}
