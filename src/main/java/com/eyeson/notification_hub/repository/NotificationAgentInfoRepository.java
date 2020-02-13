package com.eyeson.notification_hub.repository;

import com.eyeson.notification_hub.domain.NotificationAgentInfo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the NotificationAgentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationAgentInfoRepository extends MongoRepository<NotificationAgentInfo, String> {

}
