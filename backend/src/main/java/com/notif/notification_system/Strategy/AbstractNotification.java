package com.notif.notification_system.Strategy;

import com.notif.notification_system.Enum.Category;

public abstract class AbstractNotification implements NotificationChannel {
    protected void simulateSend(String type, String target, Category category, String message) {
        System.out.printf("Sending %s to %s: [%s] %s%n", type, target, category, message);
    }
    

}
