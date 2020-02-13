package com.eyeson.notification_hub.repository;

import com.eyeson.notification_hub.domain.NotificationLog;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the NotificationLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {

}
