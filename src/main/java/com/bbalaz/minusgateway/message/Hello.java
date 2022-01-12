package com.bbalaz.minusgateway.message;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Hello {
    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

}
