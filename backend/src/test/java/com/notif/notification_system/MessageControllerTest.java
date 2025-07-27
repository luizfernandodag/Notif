package com.notif.notification_system;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.notification_system.Controller.MessageController;
import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Service.NotificationService;


@Import(TestSecurityConfig.class)
@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldReturn200WhenMessageIsValid() throws Exception {
        MessageDto dto = new MessageDto();
        dto.setCategory(Category.SPORTS.name());
        dto.setContent("Match today at 18h");

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(notificationService, times(1)).sendNotificationToSubscribedUsers(any(MessageDto.class));
    }

    @Test
    public void shouldReturn400WhenMessageIsInvalid() throws Exception {
        MessageDto dto = new MessageDto();
        dto.setCategory(null); // inválido
        dto.setContent("");    // inválido

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(notificationService, times(0)).sendNotificationToSubscribedUsers(any());
    }
}