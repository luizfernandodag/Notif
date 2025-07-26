package com.notif.notification_system.Entity;

import java.time.LocalDateTime;

import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "notification_log") // ou o nome correto da tabela no seu banco
@Builder
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String userName;
    private String userEmail;
    private String userPhone;

    private Category category;
    private ChannelType channel;

    private LocalDateTime timestamp;
    private boolean sucess;
    public LocalDateTime getTimestamp() {
        // TODO Auto-generated method stub
        return this.timestamp;
    }
    


}
