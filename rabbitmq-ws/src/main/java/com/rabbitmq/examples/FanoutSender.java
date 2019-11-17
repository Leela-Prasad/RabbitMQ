package com.rabbitmq.examples;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class FanoutSender {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");

        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("scores.feed.fanout.exchange", "fanout");

            for(int i=0; i<5; ++i) {
                String message = "Test Message" + UUID.randomUUID();
                // Fanout Exchange Ignores Routing Key as it has to send to all consumer so 2 argument is ""
                channel.basicPublish("scores.feed.fanout.exchange", "", null, message.getBytes());
            }

        }
    }
}
