package bussimulator;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//  TODO hier de naam van de destination invullen
    private static String subject = "TEST_QEUE2";
    
    private Session session;
    private Connection connection;
    private MessageProducer producer;
    
    public Producer() {
    }
    
    public void sendBericht(String bericht) {
    	try {
    		createConnection();
    		sendTextMessage(bericht);
            connection.close();
    	} catch (JMSException e) {
    		e.printStackTrace();
    	}
    }
        
    
    private void createConnection() throws JMSException {
       ConnectionFactory connectionFactory =
           new ActiveMQConnectionFactory(url);
//		TODO maak de connection aan
         connection = connectionFactory.createConnection();
         connection.start();
//		TODO maak de session aan
         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		TODO maak de destination aan (gebruik de subject variabele als naam)
         Destination destination = session.createQueue(subject);
//		TODO maak de producer aan
         producer = session.createProducer(destination);  
       }
    
    
    void sendTextMessage(String themessage) throws JMSException {
//		TODO maak de message aan
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage msg = session.createTextMessage(themessage);
        // Here we are sending the message!
        producer.send(msg);
    }

	public void send(String xml) {
		// TODO Auto-generated method stub
	}    
}
