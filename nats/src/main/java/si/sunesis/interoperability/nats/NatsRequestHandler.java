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
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@ApplicationScoped
public class NatsRequestHandler extends AbstractNatsRequestHandler {

    @Inject
    public NatsRequestHandler(NatsConnection client1) {
        super(client1);
    }

    @Override
    public String processReplyRequest(String subject, byte[] data) {
        return "processReplyRequest";
    }

    @Override
    public String processStreamRequest(String subject, byte[] data) {
        return "processStreamRequest";
    }
}
