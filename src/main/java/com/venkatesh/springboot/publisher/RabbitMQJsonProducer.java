package com.venkatesh.springboot.publisher;

import com.venkatesh.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchagne;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }
    @Bean
    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent -> %s",user.toString()));
        rabbitTemplate.convertAndSend(exchagne,jsonRoutingKey,user);

    }
}
