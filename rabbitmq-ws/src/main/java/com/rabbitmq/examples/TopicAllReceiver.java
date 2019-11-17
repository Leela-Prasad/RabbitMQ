package com.rabbitmq.examples;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicAllReceiver {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        // factory.setHost("13.233.146.49");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare("scores.feed.topic.exchange", "topic");

        // Here Queue Declare Empty arguments methods will create a Non Durable, exclusive, auto delete queue.
        String queueName = channel.queueDeclare().getQueue();


        // Binding Exchange with the Queue
        channel.queueBind(queueName, "scores.feed.topic.exchange", "scores.#");

        channel.basicConsume(queueName, true, (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});

    }
}
