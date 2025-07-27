package com.notif.notification_system.Factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.notif.notification_system.Strategy.NotificationChannel;

@Component
public class NotificationSenderFactory {

    private Map<String, NotificationChannel> senders;

    public NotificationSenderFactory(Map<String, NotificationChannel> senders) {
        this.senders = senders;
    }

    public NotificationChannel getSender(String channelName) {
        NotificationChannel sender = senders.get(channelName.toUpperCase());

        if (sender == null) {
            throw new IllegalArgumentException("No sender found for channel: " + channelName);
        }

        return sender;
    }
}
