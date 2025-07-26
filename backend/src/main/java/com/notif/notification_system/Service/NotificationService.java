package com.notif.notification_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.Entity.NotificationLog;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Factory.NotificationSenderFactory;
import com.notif.notification_system.Repository.NotificationLogRepository;
import com.notif.notification_system.Strategy.NotificationChannel;

@Service
public class NotificationService {

    private final NotificationSenderFactory senderFactory;
    private final UserService userService;
    private final NotificationLogRepository notificationLogRepository;

     @Autowired
    public NotificationService(NotificationSenderFactory senderFactory, UserService userService, NotificationLogRepository notificationLogRepository) {
        this.senderFactory = senderFactory;
        this.userService = userService;
        this.notificationLogRepository = notificationLogRepository;
    }

    public List<NotificationLog> findAll()
    {
        return this.notificationLogRepository.findAll();
    }

     public void sendNotificationToSubscribedUsers(MessageDto request) {
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.isSubscribedTo(request.getCategory())) {
                for (ChannelType channel : user.getChannels()) {
                    boolean success = false;
                    try{
                    NotificationChannel sender = senderFactory.getSender(channel);
                    sender.send(user, request.getContent(), request.getCategory());
                    success = true;

                    NotificationLog log = NotificationLog.builder()
                        .userName(user.getName())
                        .userEmail(user.getEmail())
                        .userPhone(user.getPhone())
                        .category(request.getCategory())
                        .channel(channel)
                        .message(request.getContent())
                        .timestamp(java.time.LocalDateTime.now())
                        .sucess(success)
                        .build();

                    this.notificationLogRepository.save(log);


                    }
                    catch (Exception e)
                    {
                        System.err.println("Error sending notification to " + user.getName() + " via " + channel);


                    }
                }
            }
        }
    }
}