package com.notif.notification_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notif.notification_system.Entity.User;
import com.notif.notification_system.Enum.Category;

public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByEmail(String email);
      List<User> findBySubscriptionsContains(Category category);


}
