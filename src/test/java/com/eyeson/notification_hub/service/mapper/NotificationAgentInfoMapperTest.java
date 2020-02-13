package com.eyeson.notification_hub.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationAgentInfoMapperTest {

    private NotificationAgentInfoMapper notificationAgentInfoMapper;

    @BeforeEach
    public void setUp() {
        notificationAgentInfoMapper = new NotificationAgentInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(notificationAgentInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationAgentInfoMapper.fromId(null)).isNull();
    }
}
