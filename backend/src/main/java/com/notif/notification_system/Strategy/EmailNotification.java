package com.notif.notification_system.Strategy;

import org.springframework.stereotype.Component;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

@Component("emailNotification")
public class EmailNotification extends AbstractNotification {

    @Override
    public void send(User user, String message, Category category) {
        simulateSend("EMAIL", user.getEmail(), category, message);
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.EMAIL;
    }
}
