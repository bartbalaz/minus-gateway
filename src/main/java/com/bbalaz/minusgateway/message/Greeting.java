package com.bbalaz.minusgateway.message;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Greeting {
    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }
}
