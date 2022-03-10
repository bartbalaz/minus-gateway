package com.bbalaz.minusgateway.controller;

import com.bbalaz.minusgateway.message.Greeting;
import com.bbalaz.minusgateway.message.Hello;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Log4j2
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @CrossOrigin
    public Greeting greeting(Hello message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        log.info("Running experimentator");

        Integer[] array = new Integer[] { 1,2,3,4 };

        log.info("Array " + Arrays.toString(array));

        List<Integer> list =  Stream.of(array).map( e -> e*10).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for(int i: list) {
            sb.append(i + ", ");
        }

        log.info("After transformation " + sb.toString());
    }

}