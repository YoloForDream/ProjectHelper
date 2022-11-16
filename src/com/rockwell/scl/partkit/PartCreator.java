package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.Part;
import com.google.gson.Gson;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.scl.partkit.model.MaterialEntity;
import com.rockwell.scl.partkit.model.PartEntity;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * @author RWang18
 */
public class PartCreator {
    public static void main(String[] args) throws JMSException {
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
//        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        System.setProperty("com.rockwell.test.username", "dladmin");
//        System.setProperty("com.rockwell.test.password", "Kx-123456");
//        System.setProperty("HOST_ADDRESS", "172.20.11.12");
//        System.setProperty("com.rockwell.test.username", "ken");
//        System.setProperty("com.rockwell.test.password", "1");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        String ip = "192.168.59.10";
//        String ip = "172.20.11.12";
        String ip = "192.168.59.10";
        String port = "61616";
        String brokerUrl = "tcp://"+ip+":"+port;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();
        Part part = PCContext.getFunctions().getPart("") ;

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Session session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("p100");

        //Topic topic = session.createTopic("part"); Topic模式

        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        Gson gson = new Gson();
        while (true) {
            // 同步阻塞方式使用 receive()，超时之前一直等待
            // receive() 方法不带参数会一直等待
            // receive(Long timeout) 会等待指定时间后退出等待
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive != null) {
                System.out.println("接收到消息：" + receive.getText());
                String json = receive.getText();
                MaterialEntity materialEntity = new MaterialEntity();
                materialEntity = gson.fromJson(json, MaterialEntity.class);
                System.out.println("接收到消息-物料编码：" + materialEntity.getPartNumber());
                System.out.println("接收到消息-物料类型：" + materialEntity.getMaterialType());
                System.out.println("接收到消息-物料描述：" + materialEntity.getDescription());
                System.out.println("接收到消息-物料组：" + materialEntity.getMaterialGroup());
                System.out.println("接收到消息-基本单位：" + materialEntity.getUnitOfMeasure());
                System.out.println("接收到消息-产品通用名：" + materialEntity.getGmpName());
                System.out.println("接收到消息-货号：" + materialEntity.getLotNumber());
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
