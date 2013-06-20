package org.fabric3.tests.jms.client;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.net.InetAddress;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 *
 */
public class JmsClient {

    public static void main(String[] args) throws Exception {
        String bindAddress = InetAddress.getLocalHost().getHostAddress();

//        BrokerService broker = new BrokerService();
//        broker.setUseJmx(true);
//        broker.setBrokerName("testClient");
//        TransportConnector connector = broker.addConnector("tcp://" + bindAddress + ":61617");
//        String group = "domain";
//        connector.setDiscoveryUri(URI.create("multicast://default?group=" + group));
//        broker.addNetworkConnector("multicast://default?group=" + group);
//        broker.start();

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://" + bindAddress + ":61616");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic testTopic = session.createTopic("Queue1");
        MessageProducer producer = session.createProducer(testTopic);
        TextMessage message = session.createTextMessage();
        Thread.sleep(1000);
        message.setText("text");
        producer.send(message);
        System.out.println("Done");
//        broker.stop();
    }
}
