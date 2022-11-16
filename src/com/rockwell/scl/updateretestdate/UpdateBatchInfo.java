package com.rockwell.scl.updateretestdate;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.ParseException;

/**
 * @author RWang18
 */
public class UpdateBatchInfo {
    public static void main(String[] args) throws JMSException, ParseException {
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        List<String> batchNumberNotFund = new ArrayList<String>();
        String ip = "192.168.59.10";
        String port = "61616";
        String brokerUrl = "tcp://"+ip+":"+port;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("batchInfo");
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        Gson gson = new Gson();
        while (true) {
            // 同步阻塞方式使用 receive()，超时之前一直等待
            // receive() 方法不带参数会一直等待
            // receive(Long timeout) 会等待指定时间后退出等待
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive != null) {
//                System.out.println("接收到消息：" + receive.getText());
                String json = receive.getText();
                BatchEntitly batchEntitly = new BatchEntitly();
                batchEntitly=gson.fromJson(json, BatchEntitly.class);
//                System.out.println("接收到消息-批号：" +batchEntitly.getBatchNumber());
//                System.out.println("接收到消息-复验期：" + batchEntitly.getRetestDate());
                BatchInfo batchInfo = new BatchInfo();
                Boolean flag = batchInfo.updateBatchProperty(batchEntitly);
                if(!flag){
//                    batchNumberNotFund.add(batchEntitly.getBatchNumber());
                    System.out.println(batchEntitly.getBatchNumber());
                }

            }else {
                break;
            }

        }
        consumer.close();
        session.close();
        connection.close();

    }
}
