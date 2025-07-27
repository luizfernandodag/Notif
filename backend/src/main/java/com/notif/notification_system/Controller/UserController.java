package com.notif.notification_system.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notif.notification_system.DTO.UserDto;
import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*") 
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})


public class UserController {
    private final UserService userService;


    
   @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

     @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto) {
 try {
        User created = userService.addUser(dto);
        return ResponseEntity.status(201).body(created);
    } catch (Exception e) {
        e.printStackTrace(); // loga o erro completo no console
        return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
    }
    }



}
