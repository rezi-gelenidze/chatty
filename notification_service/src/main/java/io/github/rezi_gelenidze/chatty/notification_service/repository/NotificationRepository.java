package io.github.rezi_gelenidze.chatty.notification_service.repository;

import io.github.rezi_gelenidze.chatty.notification_service.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserIdOrderByTimestampDesc(String userId);
}
