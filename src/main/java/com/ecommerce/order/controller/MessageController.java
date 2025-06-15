package com.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${app.message}")
    private String msg;
    @GetMapping("/message")
    public String Message(){
        return msg;
    }
}
