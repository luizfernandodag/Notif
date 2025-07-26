package com.notif.notification_system.Strategy;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

public interface NotificationChannel {

    void send(User user, String message, Category category);
    ChannelType getChannelType();

}
