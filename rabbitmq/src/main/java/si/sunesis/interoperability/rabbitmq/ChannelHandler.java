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
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
@Getter
public class ChannelHandler {

    private final String exchangeName;
    private final String exchangeType;
    private final String routingKey;

    private final Channel channel;

    private ChannelHandler(Connection connection, String exchangeName, String exchangeType, String routingKey) throws IOException {
        this.channel = connection.createChannel();
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
        this.routingKey = routingKey;
    }

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

    public static class ChannelHandlerBuilder {
        private String exchangeName;
        private String exchangeType = "direct";
        private String routingKey = "";
        private Connection connection;

        public ChannelHandlerBuilder setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
            return this;
        }

        public ChannelHandlerBuilder setExchangeType(String exchangeType) {
            this.exchangeType = exchangeType;
            return this;
        }

        public ChannelHandlerBuilder setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public ChannelHandlerBuilder setConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public ChannelHandler build() throws IOException {
            return new ChannelHandler(connection, exchangeName, exchangeType, routingKey);
        }
    }
}
