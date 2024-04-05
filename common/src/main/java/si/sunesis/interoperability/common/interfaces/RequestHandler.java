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
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
public interface RequestHandler<T, R> {

    default void publish(T data, String subject) throws HandlerException {
        // Modbus does not support this pattern
    }

    default void subscribe(String subject, Callback<R> callback) {
        // Modbus does not support this pattern
    }

    default void requestStream(T data, String subject, String replyTo, Duration duration, Callback<R> callback) throws HandlerException {
        // Modbus does not support this pattern
        // Implement streaming logic here
    }

    default void requestReply(T data, String subject, Callback<R> callback) throws HandlerException {

    }

    default void requestReplyToMultiple(T data, String subject, String replyTo, Callback<byte[]> callback) throws HandlerException {
        // Modbus does not support this pattern
    }

    // Interface for callback
    interface Callback<R> {
        void onNext(R response);
    }
}
