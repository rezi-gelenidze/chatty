package io.github.rezi_gelenidze.chatty.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String userId;
    private String type;
    private String message;
    private LocalDateTime timestamp;
}
