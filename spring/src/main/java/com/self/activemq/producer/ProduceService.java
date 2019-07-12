package com.self.activemq.producer;

import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ProduceService {
    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String msg) {
        System.out.println("向队列" + destination + "中添加消息：" + msg);
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };
        jmsTemplate.send(destination, messageCreator);
    }

    public void sendMessage(final String msg) {
        Destination destination = jmsTemplate.getDefaultDestination();
        System.out.println("向队列" + destination + "中添加消息：" + msg);
        MessageCreator messageCreator = new MessageCreator() {
           public Message createMessage(Session session) throws JMSException {
               return session.createTextMessage(msg);
           }
        };
       jmsTemplate.send(messageCreator);
    }
}
