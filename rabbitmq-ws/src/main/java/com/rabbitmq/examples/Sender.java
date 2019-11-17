package com.rabbitmq.examples;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        // factory.setHost("13.235.48.2");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            channel.queueDeclare("hello-world", true, false, false, null);

            for(int i=0; i<5; ++i) {
                String message = "Test Message" + UUID.randomUUID();
                channel.basicPublish("", "hello-world", null, message.getBytes());
            }

        }
    }
}
