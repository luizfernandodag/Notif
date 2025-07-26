package com.notif.notification_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notif.notification_system.Entity.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long>{

}
