package com.eyeson.notification_hub.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link NotificationLogSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class NotificationLogSearchRepositoryMockConfiguration {

    @MockBean
    private NotificationLogSearchRepository mockNotificationLogSearchRepository;

}
