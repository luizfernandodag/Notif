package com.notif.notification_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notif.notification_system.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
