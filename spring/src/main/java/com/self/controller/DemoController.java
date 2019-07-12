package com.self.controller;

import com.self.activemq.consumer.ConsumerService;
import com.self.activemq.producer.ProduceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {
    // 获取配置文件中的消息队列
    @Resource
    private Destination destination;

    // 获取配置文件中的生产者
    @Resource
    private ProduceService produceService;

    // 获取配置文件中的消费者
    @Resource
    private ConsumerService consumerService;

    /**
     * 发送消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/sendMessage")
    public ModelAndView produce(@RequestParam("message") String message) {
        produceService.sendMessage(message);
        ModelAndView model = new ModelAndView();
        model.setViewName("produce");
        return model;
    }

    /**
     * 获取消息
     * @return
     */
    @RequestMapping(value = "receiveMessage", method = RequestMethod.POST)
    public void consumer(HttpServletResponse response) throws Exception {
        String result;
        TextMessage textMessage = consumerService.receive(destination);
        if (textMessage != null) {
            result = textMessage.getText();
        } else {
            result = "Empty queue error !";
        }
        System.out.println("收到消息：" + result);
        response.getWriter().write(result);
    }

}
