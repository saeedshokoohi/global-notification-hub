package com.eyeson.notification_hub.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationLogMapperTest {

    private NotificationLogMapper notificationLogMapper;

    @BeforeEach
    public void setUp() {
        notificationLogMapper = new NotificationLogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(notificationLogMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationLogMapper.fromId(null)).isNull();
    }
}
