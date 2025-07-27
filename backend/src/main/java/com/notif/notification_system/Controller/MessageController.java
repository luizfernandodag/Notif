package com.notif.notification_system.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notif.notification_system.DTO.MessageDto;
import com.notif.notification_system.DTO.NotificationLogDto;
import com.notif.notification_system.Service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 

public class MessageController {

    private final NotificationService notificationService;

    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage( @RequestBody @Valid MessageDto dto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
               String errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation error: " + errors);
           
        }
        notificationService.sendNotificationToSubscribedUsers(dto);
        //sendNotificationToSubscribedUsers(MessageDto request) {

        return ResponseEntity.ok("Message sent");

    }
        

    @GetMapping("/logs")
public List<NotificationLogDto> getAllLogs() {
    return notificationService.findAll().stream()
        .map(log -> new NotificationLogDto(
            log.getUserName(),
           log.getChannel() != null ? log.getChannel().name() : "UNKNOWN",
           log.getCategory() != null ? log.getCategory().name() : "UNKNOWN",
            log.isSuccess() ? "SENT" : "FAILED",  // converte boolean para texto
            log.getTimestamp().toString(),
            log.getMessage()
        ))
        .collect(Collectors.toList());
}

}
