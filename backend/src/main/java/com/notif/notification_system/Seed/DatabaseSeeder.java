package com.notif.notification_system.Seed;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Criando os usuários
            User alice = new User("Alice Souza", "alice@example.com", "5511999999991");
            User bruno = new User("Bruno Lima", "bruno@example.com", "5511999999992");
            User carla = new User("Carla Mendes", "carla@example.com", "5511999999993");

         
            alice.setSubscriptions(new HashSet<>(Arrays.asList(Category.SPORTS, Category.MOVIES)));
            alice.setChannels(new HashSet<>(Arrays.asList(ChannelType.EMAIL, ChannelType.PUSH)));


            bruno.setSubscriptions(new HashSet<>(Arrays.asList(Category.FINANCE, Category.MOVIES)));
            bruno.setChannels(new HashSet<>(Arrays.asList(ChannelType.SMS, ChannelType.EMAIL)));

            carla.setSubscriptions(new HashSet<>(Arrays.asList(Category.SPORTS, Category.FINANCE)));
            carla.setChannels(new HashSet<>(Arrays.asList(ChannelType.SMS, ChannelType.PUSH)));
            userRepository.saveAll(List.of(alice, bruno, carla));

            System.out.println("✅ User Seed sucessfull");
        }
    }
}
