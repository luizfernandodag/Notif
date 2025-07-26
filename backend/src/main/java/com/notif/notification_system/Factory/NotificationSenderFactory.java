package com.notif.notification_system.Factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Strategy.NotificationChannel;


@Component
public class NotificationSenderFactory {

       private final Map<ChannelType, NotificationChannel> senders = new EnumMap<>(ChannelType.class);

    @Autowired
    public NotificationSenderFactory(List<NotificationChannel> strategies) {
        for (NotificationChannel sender : strategies) {
           if (sender.getClass().isSynthetic()) {
    continue;
}
            ChannelType type = sender.getChannelType();
        
               if (type == null) {
                throw new IllegalArgumentException("Channel type cannot be null for " + sender.getClass().getSimpleName());
            }
            senders.put(sender.getChannelType(), sender);
        }
    }

    public NotificationChannel getSender(ChannelType channel) {
         NotificationChannel sender = senders.get(channel);
        if (sender == null) {
            throw new IllegalArgumentException("No sender found for channel: " + channel);
        }
        return senders.get(channel);
    }

}
