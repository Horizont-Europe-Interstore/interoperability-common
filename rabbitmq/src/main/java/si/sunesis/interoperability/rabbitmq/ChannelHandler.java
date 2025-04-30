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

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.exceptions.HandlerException;

import java.io.IOException;

/**
 * Handler class for managing RabbitMQ channel operations.
 * Handles queue and exchange declarations, message publishing, and subscription.
 * Uses the builder pattern for construction with various RabbitMQ configurations.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
@Getter
public class ChannelHandler {

    private final Connection connection;

    private final String exchangeName;
    private final String exchangeType;
    private final String routingKey;

    private final Channel channel;

    /**
     * Private constructor used by the builder.
     * Creates a new channel from the connection and initializes exchange settings.
     *
     * @param connection the RabbitMQ connection
     * @param exchangeName the name of the exchange to use
     * @param exchangeType the type of exchange (direct, fanout, topic, headers)
     * @param routingKey the routing key for message routing
     * @throws IOException if an I/O error occurs
     */
    private ChannelHandler(Connection connection, String exchangeName, String exchangeType, String routingKey) throws IOException {
        this.connection = connection;
        this.channel = connection.createChannel();
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
        this.routingKey = routingKey;
    }

    /**
     * Publishes a message to a queue.
     * If an exchange is specified, it will publish to the exchange with the routing key.
     * Otherwise, it will publish directly to the queue.
     *
     * @param data the message data to publish
     * @param queueName the name of the queue
     * @throws HandlerException if an error occurs during publishing
     */
    public void publish(String data, String queueName) throws HandlerException {
        try {
            log.debug("Publishing message to queue: {}", queueName);
            log.debug("Message: {}", data);
            if (exchangeName == null) {
                channel.queueDeclare(queueName, false, false, false, null);
                channel.basicPublish("", queueName, null, data.getBytes());
            } else {
                channel.exchangeDeclare(exchangeName, exchangeType);
                channel.basicPublish(exchangeName, routingKey, null, data.getBytes());
            }
        } catch (Exception e) {
            log.error("Failed to publish message", e);

            throw new HandlerException("Failed to publish message", e);
        }
    }

    /**
     * Subscribes to a queue to receive messages.
     * If an exchange is specified, it will bind the queue to the exchange with the routing key.
     *
     * @param queueName the name of the queue to subscribe to
     * @param callback the callback to handle received messages
     * @throws HandlerException if an error occurs during subscription
     */
    public void subscribe(String queueName, DeliverCallback callback) throws HandlerException {
        try {
            if (exchangeName != null) {
                channel.queueDeclare(queueName, false, false, false, null);
                channel.queueBind(queueName, exchangeName, routingKey);
                channel.basicConsume(queueName, true, callback, consumerTag -> {
                });
            } else {
                channel.queueDeclare(queueName, false, false, false, null);
                channel.basicConsume(queueName, true, callback, consumerTag -> {
                });
            }
        } catch (Exception e) {
            log.error("Failed to subscribe to queue", e);

            throw new HandlerException("Failed to subscribe to queue", e);
        }
    }

    /**
     * Builder class for creating ChannelHandler instances with custom configurations.
     */
    public static class ChannelHandlerBuilder {
        private String exchangeName;
        private String exchangeType = "direct";
        private String routingKey = "";
        private Connection connection;

        /**
         * Sets the exchange name for the channel handler.
         *
         * @param exchangeName the name of the exchange
         * @return this builder instance
         */
        public ChannelHandlerBuilder setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
            return this;
        }

        /**
         * Sets the exchange type for the channel handler.
         * Default is "direct" if not specified.
         *
         * @param exchangeType the type of exchange (direct, fanout, topic, headers)
         * @return this builder instance
         */
        public ChannelHandlerBuilder setExchangeType(String exchangeType) {
            this.exchangeType = exchangeType;
            return this;
        }

        /**
         * Sets the routing key for the channel handler.
         * Default is empty string if not specified.
         *
         * @param routingKey the routing key for message routing
         * @return this builder instance
         */
        public ChannelHandlerBuilder setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        /**
         * Sets the RabbitMQ connection for the channel handler.
         * This is a required parameter.
         *
         * @param connection the RabbitMQ connection
         * @return this builder instance
         */
        public ChannelHandlerBuilder setConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        /**
         * Builds a new ChannelHandler with the configured settings.
         *
         * @return the created ChannelHandler
         * @throws IOException if an I/O error occurs during channel creation
         */
        public ChannelHandler build() throws IOException {
            return new ChannelHandler(connection, exchangeName, exchangeType, routingKey);
        }
    }
}
