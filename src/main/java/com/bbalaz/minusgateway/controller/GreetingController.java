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

        Stream<Integer> stream = Stream.of(array);

        StringBuilder sb = new StringBuilder();

        stream.forEach( e -> sb.append(e).append(", "));

        log.info("Stream before: "+ sb.toString());

        stream.forEach( e-> e = e*10 );

        sb = new StringBuilder();
        stream.forEach( e -> sb.append(e).append(", "));

        log.info("Stream after " + stream.toString());
    }

}