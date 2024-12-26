package io.github.rezi_gelenidze.chatty.auth_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth/login")
    public String login() {
        return "Login endpoint in auth_service";
    }

    @GetMapping("/auth/register")
    public String register() {
        return "Register endpoint in auth_service";
    }
}
