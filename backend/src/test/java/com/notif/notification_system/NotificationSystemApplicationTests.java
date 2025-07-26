package com.notif.notification_system;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.Entity.NotificationLog;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Factory.NotificationSenderFactory;
import com.notif.notification_system.Repository.NotificationLogRepository;
import com.notif.notification_system.Service.NotificationService;
import com.notif.notification_system.Service.UserService;
import com.notif.notification_system.Strategy.NotificationChannel;

@ExtendWith(MockitoExtension.class)
class NotificationSystemApplicationTests {

    @Mock
    private NotificationSenderFactory senderFactory;

    @Mock 
    private UserService userService;

    @Mock
    private NotificationLogRepository logRepository;

    @InjectMocks
    private NotificationService notificationService; 
@Test
void shouldSendNotificationToSubscribedUsers() {
    MessageDto messageDto = new MessageDto();
    messageDto.setCategory(Category.SPORTS);
    messageDto.setContent("World cop end!");

    User user = new User();
    user.setName("João");
    user.setEmail("joao@example.com");
    user.setPhone("999999999");
    user.setSubscriptions(List.of(Category.SPORTS));
    user.setChannels(List.of(ChannelType.EMAIL));

    NotificationChannel mockSender = mock(NotificationChannel.class);

    when(userService.getAllUsers()).thenReturn(List.of(user));
    when(senderFactory.getSender(ChannelType.EMAIL)).thenReturn(mockSender);

    notificationService.sendNotificationToSubscribedUsers(messageDto);

    verify(mockSender).send(eq(user), eq("World cop end!"), eq(Category.SPORTS));
    verify(logRepository, times(1)).save(any(NotificationLog.class));
}



}
