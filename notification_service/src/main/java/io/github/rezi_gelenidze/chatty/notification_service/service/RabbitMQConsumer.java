package io.github.rezi_gelenidze.chatty.notification_service.service;

import io.github.rezi_gelenidze.chatty.notification_service.dto.*;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private final EmailService emailService;

    @Autowired
    public RabbitMQConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email-queue")
    public void receiveEmail(EmailMessage emailMessage) {
        emailService.sendEmail(
                emailMessage.getTo(),
                emailMessage.getSubject(),
                emailMessage.getHtmlContent()
        );
    }

    @RabbitListener(queues = "notification-queue")
    public void receiveNotification(NotificationMessage notificationMessage) {
        // TODO: Implement push notification sending logic
        System.out.println("Sending push notification to " + notificationMessage.getTo() + ": " + notificationMessage.getBody());
    }
}
