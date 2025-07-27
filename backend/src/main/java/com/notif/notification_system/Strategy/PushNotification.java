package com.notif.notification_system.Strategy;

import org.springframework.stereotype.Component;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

@Component("PUSH")
public class PushNotification extends AbstractNotification {

    @Override
    public void send(User user, String message, Category category) {
        simulateSend("PUSH", user.getName(), category, message);
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.PUSH;
    }
}
