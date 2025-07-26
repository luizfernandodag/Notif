package com.notif.notification_system.Entity;

import java.util.List;

import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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

@Table(name = "users") // ou o nome correto da tabela no seu banco
@Builder
public class User {
    @Id
    private Long id;

    private String name;

    private String email;

    private String phone;


    @ElementCollection(targetClass=Category.class)
    @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<Category> subscriptions;

    @ElementCollection(targetClass = ChannelType.class)
@   CollectionTable(name = "user_channels", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<ChannelType> channels;

    public boolean isSubscribedTo(Category category) {
    return this.subscriptions.contains(category);
}

}
