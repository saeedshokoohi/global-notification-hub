package com.eyeson.notification_hub.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eyeson.notification_hub.web.rest.TestUtil;

public class NotificationLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationLog.class);
        NotificationLog notificationLog1 = new NotificationLog();
        notificationLog1.setId("id1");
        NotificationLog notificationLog2 = new NotificationLog();
        notificationLog2.setId(notificationLog1.getId());
        assertThat(notificationLog1).isEqualTo(notificationLog2);
        notificationLog2.setId("id2");
        assertThat(notificationLog1).isNotEqualTo(notificationLog2);
        notificationLog1.setId(null);
        assertThat(notificationLog1).isNotEqualTo(notificationLog2);
    }
}
