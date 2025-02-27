package io.github.rezi_gelenidze.chatty.notification_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification_service__notifications")   // prefixing the collection name with the service name
public class Notification {
    @Id
    private String id;

    private String userId;

    private String type;
    private String message;

    private boolean read;
    private LocalDateTime timestamp;
}
