package io.github.rezi_gelenidze.chatty.auth_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @RequestMapping("/login/google")
    public String googleLogin(OAuth2AuthenticationToken authenticationToken) {
        // TODO: Implement Google login
        return "Google login";
    }
}
