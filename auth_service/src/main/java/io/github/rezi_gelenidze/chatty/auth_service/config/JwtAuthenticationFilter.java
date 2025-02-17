package io.github.rezi_gelenidze.chatty.auth_service.config;

import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;
import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenInvalidException;
import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenExpiredException;
import io.github.rezi_gelenidze.chatty.auth_service.exception.ManualExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // No valid token, continue without setting authentication
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix

        try {
            String username = jwtUtil.extractUsername(token);
            jwtUtil.assertType(token, JwtUtil.TokenType.ACCESS); // Ensure it's an access token

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (UsernameNotFoundException e) {
            ManualExceptionHandler.sendErrorResponse(
                    response, HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "User not found"
            );
            return;
        } catch (TokenExpiredException e) {
            ManualExceptionHandler.sendErrorResponse(
                    response, HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", "Token has expired"
            );
            return;
        } catch (TokenInvalidException e) {
            ManualExceptionHandler.sendErrorResponse(
                    response, HttpStatus.UNAUTHORIZED, "TOKEN_INVALID", "Invalid token"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}
