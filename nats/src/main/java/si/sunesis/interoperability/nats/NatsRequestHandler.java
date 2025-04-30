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
package si.sunesis.interoperability.nats;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Concrete implementation of the NATS request handler.
 * Provides specific implementations of request processing methods.
 * Can be injected as a CDI bean in a Jakarta EE application.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@ApplicationScoped
public class NatsRequestHandler extends AbstractNatsRequestHandler {

    /**
     * Constructs a new NatsRequestHandler with the specified NATS connection.
     * Uses dependency injection to get the NATS connection.
     *
     * @param client1 the NATS connection to use for communication
     */
    @Inject
    public NatsRequestHandler(NatsConnection client1) {
        super(client1);
    }

    /**
     * Processes a reply request from the specified subject.
     * This implementation simply returns a constant string.
     *
     * @param subject the subject from which the request was received
     * @param data the request data as bytes
     * @return a constant string "processReplyRequest"
     */
    @Override
    public String processReplyRequest(String subject, byte[] data) {
        return "processReplyRequest";
    }

    /**
     * Processes a stream request from the specified subject.
     * This implementation simply returns a constant string.
     *
     * @param subject the subject from which the request was received
     * @param data the request data as bytes
     * @return a constant string "processStreamRequest"
     */
    @Override
    public String processStreamRequest(String subject, byte[] data) {
        return "processStreamRequest";
    }
}
