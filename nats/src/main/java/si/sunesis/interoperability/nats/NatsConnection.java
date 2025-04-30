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

import io.nats.client.*;
import io.nats.client.impl.NatsMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.exceptions.HandlerException;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * NATS connection manager that handles connection to NATS servers.
 * Provides methods for connecting, publishing, subscribing, and request-reply communication.
 * Implements ConnectionListener to handle connection events and MessageHandler for message handling.
 * Can be injected as a CDI bean in a Jakarta EE application.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
@ApplicationScoped
public class NatsConnection implements ConnectionListener, MessageHandler {

    private static final String CONNECTION_NULL = "Connection is null";

    @Getter
    @Setter
    private Boolean reconnect = true;

    private Connection connection;

    private Dispatcher dispatcher;

    /**
     * Closes the NATS connection and releases resources.
     * Removes listeners, closes dispatchers and connections.
     */
    public void disconnect() {
        log.debug("Destroying NATS connection");

        if (getConnection().isPresent()) {
            getConnection().get().removeConnectionListener(this);
            getConnection().get().closeDispatcher(dispatcher);

            try {
                getConnection().get().close();
            } catch (InterruptedException e) {
                log.error("Error closing NATS connection", e);
                Thread.currentThread().interrupt();
            } finally {
                this.connection = null;
            }
        }
    }

    // Connect methods
    /**
     * Connects to the NATS server asynchronously with the specified options.
     *
     * @param options connection options for NATS
     * @param reconnect whether to reconnect if the connection is lost
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public void connectAsync(Options options, boolean reconnect) throws InterruptedException {
        Nats.connectAsynchronously(options, reconnect);
    }

    /**
     * Connects to the NATS server asynchronously with the specified options.
     * Uses the default reconnect setting.
     *
     * @param options connection options for NATS
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public void connectAsync(Options options) throws InterruptedException {
        connectAsync(options, reconnect);
    }

    /**
     * Connects to the NATS server asynchronously with default options.
     *
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public void connectAsync() throws InterruptedException {
        Options options = Options.builder()
                .connectionListener(this)
                .build();

        connectAsync(options);
    }

    /**
     * Connects to the NATS server synchronously with the specified options.
     *
     * @param options connection options for NATS
     * @param reconnect whether to reconnect if the connection is lost
     * @return the established NATS connection
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public Connection connectSync(Options options, boolean reconnect) throws IOException, InterruptedException {
        if (reconnect) {
            this.connection = Nats.connectReconnectOnConnect(options);
        } else {
            this.connection = Nats.connect(options);
        }

        if (dispatcher == null) {
            this.dispatcher = this.connection.createDispatcher();
        }

        return this.connection;
    }

    /**
     * Connects to the NATS server synchronously with the specified options.
     * Uses the default reconnect setting.
     *
     * @param options connection options for NATS
     * @return the established NATS connection
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public Connection connectSync(Options options) throws IOException, InterruptedException {
        return connectSync(options, reconnect);
    }

    /**
     * Connects to the NATS server synchronously with default options.
     *
     * @return the established NATS connection
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the connection attempt is interrupted
     */
    public Connection connectSync() throws IOException, InterruptedException {
        Options options = Options.builder().build();

        return connectSync(options);
    }

    /**
     * Gets the current NATS connection.
     *
     * @return an Optional containing the connection, or empty if not connected
     */
    public Optional<Connection> getConnection() {
        return Optional.ofNullable(this.connection);
    }

    /**
     * Gets the current NATS dispatcher.
     *
     * @return an Optional containing the dispatcher, or empty if not available
     */
    public Optional<Dispatcher> getDispatcher() {
        return Optional.ofNullable(this.dispatcher);
    }

    /**
     * Handles NATS connection events.
     * Updates the connection and dispatcher state based on the events.
     *
     * @param connection the NATS connection
     * @param events the connection event
     */
    @Override
    public void connectionEvent(Connection connection, Events events) {
        log.debug("Connection event: {}", events);
        log.debug("Connection: {}", connection);

        if (events == Events.CONNECTED) {
            log.debug("Connected to NATS");

            this.connection = connection;

            if (dispatcher == null) {
                this.dispatcher = this.connection.createDispatcher();
            }
        } else {
            this.connection = null;
        }
    }

    /**
     * Sends a request message and waits for a reply.
     *
     * @param message the request message to send
     * @return a CompletableFuture that completes when the reply is received
     * @throws HandlerException if the connection is null or an error occurs
     */
    public CompletableFuture<Message> requestReply(Message message) throws HandlerException {
        return getConnection().orElseThrow(() -> new HandlerException(CONNECTION_NULL)).request(message);
    }

    /**
     * Sends a request with binary data to the specified subject and waits for a reply.
     *
     * @param subject the subject to send the request to
     * @param data the binary data to send
     * @return a CompletableFuture that completes when the reply is received
     * @throws HandlerException if the connection is null or an error occurs
     */
    public CompletableFuture<Message> requestReply(String subject, byte[] data) throws HandlerException {
        Message msg = NatsMessage.builder()
                .subject(subject)
                .data(data)
                .build();

        return requestReply(msg);
    }

    /**
     * Sends a request with string data to the specified subject and waits for a reply.
     *
     * @param subject the subject to send the request to
     * @param data the string data to send
     * @return a CompletableFuture that completes when the reply is received
     * @throws HandlerException if the connection is null or an error occurs
     */
    public CompletableFuture<Message> requestReply(String subject, String data) throws HandlerException {
        Message msg = NatsMessage.builder()
                .subject(subject)
                .data(data)
                .build();

        return requestReply(msg);
    }

    /**
     * Sends a request message that can receive multiple responses.
     * This method just publishes the message with a replyTo field set.
     *
     * @param message the request message to send
     * @throws HandlerException if the connection is null or an error occurs
     */
    public void requestReplyToMultiple(Message message) throws HandlerException {
        getConnection().orElseThrow(() -> new HandlerException(CONNECTION_NULL)).publish(message);
    }

    /**
     * Sends a request message for a stream of responses.
     *
     * @param message the request message to send
     * @return a CompletableFuture that completes when the first response is received
     * @throws HandlerException if the connection is null or an error occurs
     */
    public CompletableFuture<Message> requestStream(Message message) throws HandlerException {
        return getConnection().orElseThrow(() -> new HandlerException(CONNECTION_NULL)).request(message);
    }

    /**
     * Subscribes to a subject with the specified message handler and dispatcher.
     *
     * @param subject the subject to subscribe to
     * @param handler the handler for received messages
     * @param dispatcher the dispatcher to use for message delivery
     * @return the created subscription
     */
    public Subscription subscribe(String subject, MessageHandler handler, Dispatcher dispatcher) {
        return dispatcher.subscribe(subject, handler);
    }

    /**
     * Subscribes to a subject with the specified message handler.
     * Uses the default dispatcher.
     *
     * @param subject the subject to subscribe to
     * @param handler the handler for received messages
     * @return the created subscription
     */
    public Subscription subscribe(String subject, MessageHandler handler) {
        return subscribe(subject, handler, this.dispatcher);
    }

    /**
     * Subscribes to a subject with the default message handler.
     * Uses the default dispatcher and this instance as the message handler.
     *
     * @param subject the subject to subscribe to
     * @return the created subscription
     */
    public Subscription subscribe(String subject) {
        return subscribe(subject, this);
    }

    /**
     * Publishes a message to the NATS server.
     *
     * @param msg the message to publish
     * @throws HandlerException if the connection is null or an error occurs
     */
    public void publish(Message msg) throws HandlerException {
        getConnection().orElseThrow(() -> new HandlerException(CONNECTION_NULL)).publish(msg);
    }

    /**
     * Publishes binary data to a subject with a reply-to subject.
     *
     * @param subject the subject to publish to
     * @param replyTo the reply-to subject
     * @param data the binary data to publish
     * @throws HandlerException if the connection is null or an error occurs
     */
    public void publish(String subject, String replyTo, byte[] data) throws HandlerException {
        Message msg = NatsMessage.builder()
                .subject(subject)
                .replyTo(replyTo)
                .data(data)
                .build();

        publish(msg);
    }

    /**
     * Publishes string data to a subject with a reply-to subject.
     *
     * @param subject the subject to publish to
     * @param replyTo the reply-to subject
     * @param data the string data to publish
     * @throws HandlerException if the connection is null or an error occurs
     */
    public void publish(String subject, String replyTo, String data) throws HandlerException {
        Message msg = NatsMessage.builder()
                .subject(subject)
                .replyTo(replyTo)
                .data(data)
                .build();

        publish(msg);
    }

    /**
     * Default message handler implementation.
     * Logs the received message and acknowledges it.
     *
     * @param message the received NATS message
     */
    @Override
    public void onMessage(Message message) {
        log.debug("Received message: {}", message);

        message.ack();
    }
}
