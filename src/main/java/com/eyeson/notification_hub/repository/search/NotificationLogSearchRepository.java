package com.eyeson.notification_hub.repository.search;

import com.eyeson.notification_hub.domain.NotificationLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link NotificationLog} entity.
 */
public interface NotificationLogSearchRepository extends ElasticsearchRepository<NotificationLog, String> {
}
