package com.venkatesh.springboot.controller;

import com.venkatesh.springboot.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;
    public MessageController(RabbitMQProducer rabbitMQProducer){
        this.rabbitMQProducer=rabbitMQProducer;
    }
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent -> "+message);
    }
}
