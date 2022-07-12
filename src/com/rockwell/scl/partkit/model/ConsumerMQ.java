package com.rockwell.scl.partkit.model;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.List;

public class ConsumerMQ {
    String host;
    String port;
    String queueName;
    public ConsumerMQ( String host, String port, String queueName){
        this.host =host;
        this.port = port;
        this.queueName = queueName;
    }
    public void getMessageFromMQ() throws JMSException {
        String brokerUrl = "tcp://"+host+":"+port;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        Gson gson = new Gson();
        while (true) {
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive != null) {
                String json = receive.getText();
                MaterialEntity materialEntity = new MaterialEntity();
                materialEntity = gson.fromJson(json, MaterialEntity.class);
                PartEntity partEntity = new PartEntity();
                partEntity.createOrUpdatePartObject( materialEntity);
            }else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();
    }
}
