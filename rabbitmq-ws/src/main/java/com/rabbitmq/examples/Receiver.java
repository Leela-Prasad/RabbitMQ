package com.rabbitmq.examples;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        // factory.setHost("13.233.146.49");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello-world", true, false, false, null);

        channel.basicConsume("hello-world", true, (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});

    }
}
