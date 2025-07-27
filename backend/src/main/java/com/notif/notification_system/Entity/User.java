package com.notif.notification_system.Entity;

import java.util.Set;

import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "users") 
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phone;


    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Category> subscriptions;

    @ElementCollection(targetClass = ChannelType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_channels", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ChannelType> channels;

   

    public User(@NotBlank String name, @Email @NotBlank String email, @NotBlank String phone,
            Set<Category> subscriptions, Set<ChannelType> channels) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscriptions = subscriptions;
        this.channels = channels;
    }

    public User(@NotBlank String name, @Email @NotBlank String email, @NotBlank String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

   public boolean isSubscribedTo(String category) {
    for (Category sub : subscriptions) {
        if (sub.name().equalsIgnoreCase(category)) {
            return true;
        }
    }
    return false;
}
}
