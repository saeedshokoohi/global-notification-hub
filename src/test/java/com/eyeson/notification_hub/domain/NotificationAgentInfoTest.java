package com.eyeson.notification_hub.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eyeson.notification_hub.web.rest.TestUtil;

public class NotificationAgentInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationAgentInfo.class);
        NotificationAgentInfo notificationAgentInfo1 = new NotificationAgentInfo();
        notificationAgentInfo1.setId("id1");
        NotificationAgentInfo notificationAgentInfo2 = new NotificationAgentInfo();
        notificationAgentInfo2.setId(notificationAgentInfo1.getId());
        assertThat(notificationAgentInfo1).isEqualTo(notificationAgentInfo2);
        notificationAgentInfo2.setId("id2");
        assertThat(notificationAgentInfo1).isNotEqualTo(notificationAgentInfo2);
        notificationAgentInfo1.setId(null);
        assertThat(notificationAgentInfo1).isNotEqualTo(notificationAgentInfo2);
    }
}
