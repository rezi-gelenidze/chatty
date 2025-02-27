package io.github.rezi_gelenidze.chatty.notification_service.controller;

import io.github.rezi_gelenidze.chatty.notification_service.entity.Notification;
import io.github.rezi_gelenidze.chatty.notification_service.service.PushNotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;
    private final SimpMessagingTemplate messagingTemplate;

    public PushNotificationController(PushNotificationService pushNotificationService, SimpMessagingTemplate messagingTemplate) {
        this.pushNotificationService = pushNotificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(@PathVariable String userId) {
        return pushNotificationService.getUserNotifications(userId);
    }

    @PatchMapping("/read")
    public void markNotificationsAsRead(@RequestBody List<String> notificationIds) {
        pushNotificationService.markAsRead(notificationIds);
    }
}
