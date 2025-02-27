package io.github.rezi_gelenidze.chatty.notification_service.service;

import io.github.rezi_gelenidze.chatty.notification_service.dto.NotificationMessage;
import io.github.rezi_gelenidze.chatty.notification_service.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import io.github.rezi_gelenidze.chatty.notification_service.entity.Notification;

import java.util.List;

@Service
public class PushNotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;

    public PushNotificationService(SimpMessagingTemplate messagingTemplate, NotificationRepository notificationRepository) {
        this.messagingTemplate = messagingTemplate;
        this.notificationRepository = notificationRepository;
    }

    public void sendPushNotification(String userId, NotificationMessage notification) {
        // If user is online, we can send notification via WebSocket
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", notification);

        // We store notification in database, so user can see it later
        Notification newNotification = new Notification();
        newNotification.setUserId(userId);
        newNotification.setType(notification.getType());
        newNotification.setMessage(notification.getMessage());
        newNotification.setTimestamp(notification.getTimestamp());
        newNotification.setRead(false);

        notificationRepository.save(newNotification);
    }

    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    public void markAsRead(List<String> notificationIds) {
        notificationRepository.findAllById(notificationIds).forEach(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }
}
