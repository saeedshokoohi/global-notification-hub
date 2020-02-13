package com.eyeson.notification_hub.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eyeson.notification_hub.web.rest.TestUtil;

public class NotificationLogDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationLogDTO.class);
        NotificationLogDTO notificationLogDTO1 = new NotificationLogDTO();
        notificationLogDTO1.setId("id1");
        NotificationLogDTO notificationLogDTO2 = new NotificationLogDTO();
        assertThat(notificationLogDTO1).isNotEqualTo(notificationLogDTO2);
        notificationLogDTO2.setId(notificationLogDTO1.getId());
        assertThat(notificationLogDTO1).isEqualTo(notificationLogDTO2);
        notificationLogDTO2.setId("id2");
        assertThat(notificationLogDTO1).isNotEqualTo(notificationLogDTO2);
        notificationLogDTO1.setId(null);
        assertThat(notificationLogDTO1).isNotEqualTo(notificationLogDTO2);
    }
}
