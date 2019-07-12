package com.self.activemq.consumer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class ConsumerService {
    @Resource
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination) {
        // 获取队列中的消息
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);

        if (textMessage != null) {
            try {
                String message = textMessage.getText();
                System.out.println("获取消息：队列（" + destination + "）中的消息为：" + message);
            } catch (JMSException e) {
                e.printStackTrace();
            }

            System.out.println();
        }
        return textMessage;
    }

}
