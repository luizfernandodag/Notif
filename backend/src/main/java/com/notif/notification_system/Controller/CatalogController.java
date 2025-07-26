package com.notif.notification_system.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notif.notification_system.Enum.Category;
import com.notif.notification_system.Enum.ChannelType;


@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "*") 

public class CatalogController {

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(Arrays.asList(Category.values()));
    }

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelType>> getChannels() {
        return ResponseEntity.ok(Arrays.asList(ChannelType.values()));
    }

}
