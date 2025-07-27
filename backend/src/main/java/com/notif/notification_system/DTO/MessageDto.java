package com.notif.notification_system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class MessageDto {
    @NotNull(message = "Category must not be null.")
    private String category;

    @NotBlank(message = "Content must not be blank.")
    private String content;

    public MessageDto() {}

    public MessageDto(String category, String content) {
        this.category = category;
        this.content = content;
    }

    
}