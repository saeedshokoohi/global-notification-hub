package com.eyeson.notification_hub.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eyeson.notification_hub.web.rest.TestUtil;

public class NotificationAgentInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationAgentInfoDTO.class);
        NotificationAgentInfoDTO notificationAgentInfoDTO1 = new NotificationAgentInfoDTO();
        notificationAgentInfoDTO1.setId("id1");
        NotificationAgentInfoDTO notificationAgentInfoDTO2 = new NotificationAgentInfoDTO();
        assertThat(notificationAgentInfoDTO1).isNotEqualTo(notificationAgentInfoDTO2);
        notificationAgentInfoDTO2.setId(notificationAgentInfoDTO1.getId());
        assertThat(notificationAgentInfoDTO1).isEqualTo(notificationAgentInfoDTO2);
        notificationAgentInfoDTO2.setId("id2");
        assertThat(notificationAgentInfoDTO1).isNotEqualTo(notificationAgentInfoDTO2);
        notificationAgentInfoDTO1.setId(null);
        assertThat(notificationAgentInfoDTO1).isNotEqualTo(notificationAgentInfoDTO2);
    }
}
