package com.notif.notification_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notif.notification_system.Entity.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long>{

List<NotificationLog> findAllByOrderByTimestampDesc();



}
