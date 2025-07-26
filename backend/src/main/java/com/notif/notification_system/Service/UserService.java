package com.notif.notification_system.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

import jakarta.annotation.PostConstruct;
@Service
public class UserService {

        private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users.add(User.builder()
                .id(1L)
                .name("Maria Silva")
                .email("maria@email.com")
                .phone("+5511999999999")
                .subscriptions(Arrays.asList(Category.SPORTS, Category.MOVIES))
                .channels(Arrays.asList(ChannelType.EMAIL, ChannelType.SMS))
                .build());

        users.add(User.builder()
                .id(2L)
                .name("João Souza")
                .email("joao@email.com")
                .phone("+5511988888888")
                .subscriptions(List.of(Category.FINANCE))
                .channels(Arrays.asList(ChannelType.PUSH, ChannelType.EMAIL))
                .build());

        users.add(User.builder()
                .id(3L)
                .name("Ana Oliveira")
                .email("ana@email.com")
                .phone("+5511977777777")
                .subscriptions(Arrays.asList(Category.MOVIES, Category.FINANCE))
                .channels(List.of(ChannelType.PUSH))
                .build());
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<User> getUsersSubscribedTo(Category category) {
        return users.stream()
                .filter(user -> user.getSubscriptions().contains(category))
                .toList();
    }

}
