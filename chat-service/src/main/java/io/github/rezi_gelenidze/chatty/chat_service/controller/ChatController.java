package io.github.rezi_gelenidze.chatty.chat_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @GetMapping
    public String get() {
        return "Hello from chat service!";
    }
}
