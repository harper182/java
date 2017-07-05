package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by hbowang on 7/5/17.
 */
public class RabbitCustomer {
    public static String QUEUE_NAME="TEST";
    public Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        return factory.newConnection();
    }
    public Connection getConnection1() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://localhost:5672");
        return factory.newConnection();
    }
    public void receiveMsg() throws IOException, TimeoutException {
        Connection connection = this.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Customer Waiting Received messages");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("Customer Received '" + msg + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
    public void receiveMsg1() throws IOException, TimeoutException {
        Connection connection = this.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("Customer Waiting Received messages");
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("Customer Received '" + msg + "'");
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
    public static void main(String[] args) throws IOException, TimeoutException {
        new RabbitCustomer().receiveMsg1();
    }
}
