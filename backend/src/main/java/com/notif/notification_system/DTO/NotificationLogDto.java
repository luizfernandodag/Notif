package com.notif.notification_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogDto {
    private String user;
    private String channel;
    private String category;
    private String status;
    private String timestamp;
    private String message;
}