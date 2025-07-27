package com.notif.notification_system;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Factory.NotificationSenderFactory;
import com.notif.notification_system.Repository.NotificationLogRepository;
import com.notif.notification_system.Service.NotificationService;
import com.notif.notification_system.Service.UserService;
import com.notif.notification_system.Strategy.NotificationChannel;

public class NotificationSystemApplicationTests {

    private NotificationService notificationService;

    @Mock
    private NotificationSenderFactory senderFactory;

    @Mock
    private UserService userService;

    @Mock
    private NotificationLogRepository logRepository;

    @Mock
    private NotificationChannel notificationChannel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationService(senderFactory, userService, logRepository);
    }

    @Test
    public void shouldSendNotificationToSubscribedUsers() {
        // Arrange
        User user = User.builder()
            .name("Test User")
            .email("test@example.com")
            .phone("5511999999999")
            .subscriptions(Set.of(Category.SPORTS)) // usa enum Category
            .channels(Set.of(ChannelType.EMAIL))   // usa enum ChannelTypeIÚSCULO: string simples para o canal
            .build();

        when(userService.getAllUserEntities()).thenReturn(List.of(user));

        // Mock do factory para retornar o mock notificationChannel para qualquer chave maiúscula "EMAIL"
        when(senderFactory.getSender(anyString())).thenAnswer(invocation -> {
            String channelName = invocation.getArgument(0).toString().toUpperCase();
            if ("EMAIL".equals(channelName)) {
                return notificationChannel;
            }
            throw new IllegalArgumentException("No sender found for channel: " + channelName);
        });

        MessageDto dto = new MessageDto("SPORTS", "World cup end!");

        // Act
        notificationService.sendNotificationToSubscribedUsers(dto);

        // Assert
        verify(notificationChannel, times(1)).send(
            eq(user),
            eq("World cup end!"),
            eq(Category.SPORTS)
        );

        verify(logRepository, atLeastOnce()).save(any());
    }
}
