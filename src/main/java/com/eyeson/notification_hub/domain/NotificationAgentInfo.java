package com.eyeson.notification_hub.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.ZonedDateTime;

import com.eyeson.notification_hub.domain.enumeration.NotificationType;

import com.eyeson.notification_hub.domain.enumeration.NotificationCenter;

import com.eyeson.notification_hub.domain.enumeration.NotificationAgentStatus;

/**
 * A NotificationAgentInfo.
 */
@Document(collection = "notification_agent_info")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notificationagentinfo")
public class NotificationAgentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("create_date")
    private ZonedDateTime createDate;

    @Field("notification_type")
    private NotificationType notificationType;

    @Field("notification_center")
    private NotificationCenter notificationCenter;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("customer_id")
    private Long customerId;

    @Field("token")
    private String token;

    @Field("sender")
    private String sender;

    @Field("status")
    private NotificationAgentStatus status;

    @Field("is_deleted")
    private Boolean isDeleted;

    @Field("description")
    private String description;

    @Field("agent_config")
    private String agentConfig;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public NotificationAgentInfo title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NotificationAgentInfo createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public NotificationAgentInfo notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationCenter getNotificationCenter() {
        return notificationCenter;
    }

    public NotificationAgentInfo notificationCenter(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
        return this;
    }

    public void setNotificationCenter(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
    }

    public String getUsername() {
        return username;
    }

    public NotificationAgentInfo username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public NotificationAgentInfo password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public NotificationAgentInfo customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getToken() {
        return token;
    }

    public NotificationAgentInfo token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSender() {
        return sender;
    }

    public NotificationAgentInfo sender(String sender) {
        this.sender = sender;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public NotificationAgentStatus getStatus() {
        return status;
    }

    public NotificationAgentInfo status(NotificationAgentStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(NotificationAgentStatus status) {
        this.status = status;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NotificationAgentInfo isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDescription() {
        return description;
    }

    public NotificationAgentInfo description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgentConfig() {
        return agentConfig;
    }

    public NotificationAgentInfo agentConfig(String agentConfig) {
        this.agentConfig = agentConfig;
        return this;
    }

    public void setAgentConfig(String agentConfig) {
        this.agentConfig = agentConfig;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationAgentInfo)) {
            return false;
        }
        return id != null && id.equals(((NotificationAgentInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationAgentInfo{" +
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
