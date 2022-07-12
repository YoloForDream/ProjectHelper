package com.rockwell.scl.filter;

import com.google.gson.Gson;
import com.rockwell.scl.filter.datahandle.FilterHelper;
import com.rockwell.scl.filter.model.FilterEntity;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author RWang18
 */
public class CreateFilter {
    public static void main(String[] args) throws Exception {
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        System.setProperty("com.rockwell.test.username", "ken");
//        System.setProperty("com.rockwell.test.password", "1");
//        System.setProperty("HOST_ADDRESS", "192.168.108.159");
//        String ip = "192.168.108.159";
        String ip = "192.168.59.10";
//        192.168.108.53
//        "192.168.59.10";
        String port = "61616";
        String brokerUrl = "tcp://"+ip+":"+port;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("prd1");
        MessageConsumer consumer = session.createConsumer(queue);
        Gson gson = new Gson();
        while (true) {
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive != null) {
                System.out.println("接收到消息：" + receive.getText());
                String json = receive.getText();
                FilterEntity filterEntity = gson.fromJson(json, FilterEntity.class);
                System.out.println("1：" + filterEntity.getHasCollectedAsConsume());
                System.out.println("2：" + filterEntity.getFilterOpenDate());
                System.out.println("3：" + filterEntity.getUsageCountUpLimit());
                if(!filterEntity.getSerialNo().equals("NA")){
                    FilterHelper filterHelper = new FilterHelper();
                    filterHelper.createOrUpdateFilter(filterEntity);
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
