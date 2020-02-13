package com.eyeson.notification_hub.repository.search;

import com.eyeson.notification_hub.domain.NotificationAgentInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link NotificationAgentInfo} entity.
 */
public interface NotificationAgentInfoSearchRepository extends ElasticsearchRepository<NotificationAgentInfo, String> {
}
