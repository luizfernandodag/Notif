package com.notif.notification_system.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.Entity.NotificationLog;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Factory.NotificationSenderFactory;
import com.notif.notification_system.Repository.NotificationLogRepository;
import com.notif.notification_system.Strategy.NotificationChannel;

@Service
public class NotificationService {

    private final NotificationSenderFactory senderFactory;
    private final UserService userService;
    private final NotificationLogRepository notificationLogRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationSenderFactory senderFactory,
                               UserService userService,
                               NotificationLogRepository notificationLogRepository) {
        this.senderFactory = senderFactory;
        this.userService = userService;
        this.notificationLogRepository = notificationLogRepository;
    }

    public List<NotificationLog> findAll() {
        return this.notificationLogRepository.findAllByOrderByTimestampDesc();
    }

    public void sendNotificationToSubscribedUsers(MessageDto dto) {
        String categoryStr = dto.getCategory().toUpperCase();
        String content = dto.getContent();

        Category categoryEnum;
        try {
            categoryEnum = Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            logger.error("❌ Invalid category received: {}", categoryStr);
            return;
        }

        List<User> users = userService.getAllUserEntities();

        for (User user : users) {
            // Agora o método isSubscribedTo recebe Category
            if (!user.isSubscribedTo(categoryEnum.name())) continue;

            // user.getChannels() já retorna Set<ChannelType>
            for (ChannelType channel : user.getChannels()) {
                boolean success = false;

                try {
                    NotificationChannel sender = senderFactory.getSender(channel.name());
                    sender.send(user, content, categoryEnum);
                    success = true;
                    logger.info("✅ Message sent to {} via {}", user.getEmail(), channel);
                } catch (Exception e) {
                    logger.error("❌ Failed to send to {} via {}: {}", user.getEmail(), channel, e.getMessage());
                }

                NotificationLog log = NotificationLog.builder()
                    .userName(user.getName())
                    .userEmail(user.getEmail())
                    .userPhone(user.getPhone())
                    .message(content)
                    .category(categoryEnum)
                    .channel(channel)
                    .timestamp(LocalDateTime.now())
                    .success(success)
                    .build();

                notificationLogRepository.save(log);
            }
        }
    }
}
