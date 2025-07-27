package com.notif.notification_system.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.notification_system.DTO.UserDto;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;
import com.notif.notification_system.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Retorna as entidades diretamente
    public List<User> getAllUserEntities() {
        return userRepository.findAll();
    }

    // Retorna DTOs para exibição
   public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> new UserDto(
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getSubscriptions().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()),
            user.getChannels().stream()
                .map(Enum::name)
                .collect(Collectors.toSet())
        ))
        .collect(Collectors.toList());
}

    // Filtra usuários inscritos em uma categoria
    public List<User> getUsersSubscribedTo(Category category) {
        return userRepository.findBySubscriptionsContains(category);
    }

    public UserDto toDto(User user) {
    return new UserDto(
        user.getName(),
        user.getEmail(),
        user.getPhone(),
        user.getSubscriptions().stream().map(Enum::name).collect(Collectors.toSet()),
        user.getChannels().stream().map(Enum::name).collect(Collectors.toSet())
    );
    }

    // Adiciona usuário a partir do DTO
   public User addUser(UserDto dto) {
    Set<Category> categories = dto.getSubscriptions().stream()
        .map(Category::valueOf)  // converte String para enum
        .collect(Collectors.toSet());

    Set<ChannelType> channels = dto.getChannels().stream()
        .map(ChannelType::valueOf) // converte String para enum
        .collect(Collectors.toSet());

    User user = User.builder()
        .name(dto.getName())
        .email(dto.getEmail())
        .phone(dto.getPhone())
        .subscriptions(categories)
        .channels(channels)
        .build();

    return userRepository.save(user);
}

}