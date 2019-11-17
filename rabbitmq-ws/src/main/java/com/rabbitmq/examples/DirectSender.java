package com.rabbitmq.examples;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class DirectSender {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");

        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("scores.feed.direct.exchange", "direct");

            for(int i=0; i<5; ++i) {
                String message = "Cricket Message " + UUID.randomUUID();
                channel.basicPublish("scores.feed.direct.exchange", "queue.cricket", null, message.getBytes());
            }

            for(int i=0; i<5; ++i) {
                String message = "Hockey Message " + UUID.randomUUID();
                channel.basicPublish("scores.feed.direct.exchange", "queue.hockey", null, message.getBytes());
            }

            for(int i=0; i<5; ++i) {
                String message = "Football Message " + UUID.randomUUID();
                channel.basicPublish("scores.feed.direct.exchange", "queue.football", null, message.getBytes());
            }
        }
    }
}
