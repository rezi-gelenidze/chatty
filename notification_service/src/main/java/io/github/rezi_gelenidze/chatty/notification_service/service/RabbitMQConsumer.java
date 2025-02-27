package io.github.rezi_gelenidze.chatty.notification_service.service;

import io.github.rezi_gelenidze.chatty.notification_service.dto.*;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQConsumer {
    private final EmailService emailService;
    private final PushNotificationService pushNotificationService;

    @Autowired
    public RabbitMQConsumer(EmailService emailService, PushNotificationService pushNotificationService) {
        this.emailService = emailService;
        this.pushNotificationService = pushNotificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.email}")
    public void receiveEmail(EmailMessage emailMessage) {
        emailService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getHtmlContent());
    }

    @RabbitListener(queues = "${rabbitmq.queue.push-notification}")
    public void receivePushNotification(NotificationMessage notificationMessage) {
        pushNotificationService.sendPushNotification(notificationMessage.getUserId(), notificationMessage);
    }
}
