package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by hbowang on 7/5/17.
 */
public class RabbitProducer {
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
    public void sendMsg(String msg) throws IOException, TimeoutException {
        Connection connection = this.getConnection();
        Channel channel = connection.createChannel();
        int count = 0;
        while (count++ < 60) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.basicPublish("", QUEUE_NAME, null, (msg+new Date().toLocaleString()).getBytes());
            System.out.println("Producer Send +'" + msg + "'"+new Date().toGMTString());
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
    public void sendMsg1(String msg) throws IOException, TimeoutException {
        Connection connection = this.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        for (int i =0 ; i < 2000; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String cusMsg = msg +" "+i +":::" + new Date().toGMTString();
            channel.basicPublish("",QUEUE_NAME, MessageProperties.TEXT_PLAIN, cusMsg.getBytes());
            System.out.println("Producer Send +'" + cusMsg + "'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
    public static void main(String[] args) throws IOException, TimeoutException {
        new RabbitProducer().sendMsg1("hello rabbit");
//        new RabbitProducer().sendMsg("hello rabbit");
    }
}
