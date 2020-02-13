package com.eyeson.notification_hub.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import com.eyeson.notification_hub.domain.enumeration.NotificationType;
import com.eyeson.notification_hub.domain.enumeration.NotificationCenter;
import com.eyeson.notification_hub.domain.enumeration.NotificationAgentStatus;

/**
 * A DTO for the {@link com.eyeson.notification_hub.domain.NotificationAgentInfo} entity.
 */
public class NotificationAgentInfoDTO implements Serializable {

    private String id;

    private String title;

    private ZonedDateTime createDate;

    private NotificationType notificationType;

    private NotificationCenter notificationCenter;

    private String username;

    private String password;

    private Long customerId;

    private String token;

    private String sender;

    private NotificationAgentStatus status;

    private Boolean isDeleted;

    private String description;

    private String agentConfig;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationCenter getNotificationCenter() {
        return notificationCenter;
    }

    public void setNotificationCenter(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public NotificationAgentStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationAgentStatus status) {
        this.status = status;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgentConfig() {
        return agentConfig;
    }

    public void setAgentConfig(String agentConfig) {
        this.agentConfig = agentConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationAgentInfoDTO notificationAgentInfoDTO = (NotificationAgentInfoDTO) o;
        if (notificationAgentInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationAgentInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationAgentInfoDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", notificationType='" + getNotificationType() + "'" +
            ", notificationCenter='" + getNotificationCenter() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", customerId=" + getCustomerId() +
            ", token='" + getToken() + "'" +
            ", sender='" + getSender() + "'" +
            ", status='" + getStatus() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", description='" + getDescription() + "'" +
            ", agentConfig='" + getAgentConfig() + "'" +
            "}";
    }
}
