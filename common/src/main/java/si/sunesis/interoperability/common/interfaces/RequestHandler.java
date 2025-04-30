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
package si.sunesis.interoperability.common.interfaces;

import si.sunesis.interoperability.common.exceptions.HandlerException;

import java.time.Duration;

/**
 * Interface defining the contract for request handlers across different protocols.
 * Implementations handle publish-subscribe and request-reply patterns.
 *
 * @param <T> The type of data that can be published or sent in requests
 * @param <R> The type of data that can be received in responses or subscriptions
 * 
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
public interface RequestHandler<T, R> {

    /**
     * Publishes data to a specific subject without expecting a reply.
     *
     * @param data The data to publish
     * @param subject The subject/topic to publish to
     * @throws HandlerException If an error occurs during publishing
     */
    default void publish(T data, String subject) throws HandlerException {
        // Modbus does not support this pattern
    }

    /**
     * Subscribes to a subject to receive messages.
     *
     * @param subject The subject/topic to subscribe to
     * @param callback The callback to handle received messages
     */
    default void subscribe(String subject, Callback<R> callback) {
        // Modbus does not support this pattern
    }

    /**
     * Sends a request and establishes a stream of responses.
     *
     * @param data The request data
     * @param subject The subject/topic to send the request to
     * @param replyTo The subject/topic where responses should be sent
     * @param duration The duration for which to maintain the stream
     * @param callback The callback to handle received responses
     * @throws HandlerException If an error occurs during the request
     */
    default void requestStream(T data, String subject, String replyTo, Duration duration, Callback<R> callback) throws HandlerException {
        // Modbus does not support this pattern
        // Implement streaming logic here
    }

    /**
     * Sends a request and expects a single reply.
     *
     * @param data The request data
     * @param subject The subject/topic to send the request to
     * @param callback The callback to handle the response
     * @throws HandlerException If an error occurs during the request
     */
    default void requestReply(T data, String subject, Callback<R> callback) throws HandlerException {

    }

    /**
     * Publishes data to a specific subject using a specified method.
     *
     * @param data The data to publish
     * @param subject The subject/topic to publish to
     * @param method The method to be used for publishing
     * @throws HandlerException If an error occurs during publishing
     */
    default void publish(T data, String subject, String method) throws HandlerException {
        // Modbus does not support this pattern
    }

    /**
     * Sends a request using a specified method and expects a single reply.
     *
     * @param data The request data
     * @param subject The subject/topic to send the request to
     * @param method The method to be used for the request
     * @param callback The callback to handle the response
     * @throws HandlerException If an error occurs during the request
     */
    default void requestReply(T data, String subject,  String method, Callback<R> callback) throws HandlerException {

    }

    /**
     * Sends a request that can receive multiple responses on a specified reply topic.
     *
     * @param data The request data
     * @param subject The subject/topic to send the request to
     * @param replyTo The subject/topic where responses should be sent
     * @param callback The callback to handle the raw byte array responses
     * @throws HandlerException If an error occurs during the request
     */
    default void requestReplyToMultiple(T data, String subject, String replyTo, Callback<byte[]> callback) throws HandlerException {
        // Modbus does not support this pattern
    }

    /**
     * Disconnects the handler, releasing any resources.
     */
    void disconnect();

    /**
     * Interface for callback functions that handle responses.
     *
     * @param <R> The type of response data
     */
    interface Callback<R> {
        /**
         * Called when a new response is received.
         *
         * @param response The received response
         */
        void onNext(R response);
    }
}
