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

import io.nats.client.Message;
import io.nats.client.impl.Headers;
import io.nats.client.impl.NatsMessage;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.constants.Constants;
import si.sunesis.interoperability.common.exceptions.HandlerException;
import si.sunesis.interoperability.common.interfaces.RequestHandler;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractNatsRequestHandler extends AbstractRequestHandler<NatsConnection, Message> implements RequestHandler<String, byte[]> {

    protected AbstractNatsRequestHandler(NatsConnection client) {
        this.client = client;
    }

    @Override
    public NatsConnection getClient() {
        return this.client;
    }

    @Override
    public void publish(String data, String subject) throws HandlerException {
        log.debug("Publishing message: {} to topic: {}", data, subject);

        if (data == null || data.isEmpty()) {
            log.error("Data is empty, topic: {}", subject);
            return;
        }

        Message msg = NatsMessage.builder()
                .subject(subject)
                .data(data)
                .build();

        client.publish(msg);
    }

    @Override
    public void subscribe(String subject, Callback<byte[]> callback) {
        log.debug("Subscribing to subject: {}", subject);

        client.subscribe(subject, message -> {
            if (message.getReplyTo() != null) {
                if (message.hasHeaders() && message.getHeaders().containsKey(Constants.DURATION)) {
                    handleStream(subject, message);
                    callback.onNext("Handled stream".getBytes(StandardCharsets.UTF_8));
                } else {
                    handleRequestReply(subject, message);
                    callback.onNext("Handled request".getBytes(StandardCharsets.UTF_8));
                }
            } else {
                callback.onNext(message.getData());
            }
        });
    }

    @Override
    public void requestStream(String request, String subject, String replyTo, Duration duration, Callback<byte[]> callback) throws HandlerException {
        client.subscribe(replyTo, message -> callback.onNext(message.getData()));

        Headers headers = new Headers();
        headers.add(Constants.DURATION, duration.toString());

        Message message = NatsMessage.builder()
                .subject(subject)
                .replyTo(replyTo)
                .data(request)
                .headers(headers)
                .build();

        client.requestReplyToMultiple(message);
    }

    @Override
    public void requestReplyToMultiple(String request, String subject, String replyTo, Callback<byte[]> callback) throws HandlerException {
        client.subscribe(replyTo, message -> callback.onNext(message.getData()));

        Message msg = NatsMessage.builder()
                .subject(subject)
                .replyTo(replyTo)
                .data(request)
                .build();

        client.requestReplyToMultiple(msg);
    }

    @Override
    public void requestReply(String request, String subject, Callback<byte[]> callback) throws HandlerException {
        Message msg = NatsMessage.builder()
                .subject(subject)
                .data(request)
                .build();

        callback.onNext(client.requestReply(msg).join().getData());
    }

    @Override
    protected void handleRequestReply(String subject, Message message) {
        String reply = processReplyRequest(subject, message.getData());

        Message msg = NatsMessage.builder()
                .subject(message.getReplyTo())
                .data(reply)
                .build();

        try {
            client.publish(msg);
        } catch (HandlerException e) {
            log.error("Error while publishing reply", e);
        }
    }

    @Override
    protected void handleStream(String subject, Message message) {
        try {
            Headers headers = message.getHeaders();
            if (headers == null || headers.get(Constants.DURATION) == null || headers.get(Constants.DURATION).isEmpty()) {
                throw new IllegalArgumentException("Duration header is missing");
            }

            Duration duration = Duration.parse(headers.get(Constants.DURATION).get(0));

            int numOfMessages = Math.toIntExact(duration.toSeconds());
            for (int iii = 0; iii < numOfMessages; iii++) {
                String reply = processStreamRequest(subject, message.getData());

                Message msg = NatsMessage.builder()
                        .subject(message.getReplyTo())
                        .data(reply)
                        .build();
                client.publish(msg);

                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            log.error("Error while publishing stream", e);
        }
    }

    @Override
    public void disconnect() {
        if (client != null) {
            client.disconnect();
        }
    }
}

