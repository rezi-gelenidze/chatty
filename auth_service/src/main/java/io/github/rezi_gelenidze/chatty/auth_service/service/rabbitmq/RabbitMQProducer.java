package io.github.rezi_gelenidze.chatty.auth_service.service.rabbitmq;

import io.github.rezi_gelenidze.chatty.auth_service.dto.rabbitmq.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key.email}")
    private String emailRoutingKey;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailNotification(String to, String subject, String htmlContent) {
        EmailMessage emailMessage = new EmailMessage(to, subject, htmlContent);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, emailMessage);
    }
}
