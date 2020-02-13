package com.eyeson.notification_hub.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import com.eyeson.notification_hub.domain.enumeration.NotificationStatus;

/**
 * A DTO for the {@link com.eyeson.notification_hub.domain.NotificationLog} entity.
 */
public class NotificationLogDTO implements Serializable {

    private String id;

    private ZonedDateTime createDate;

    private Long consumerAppId;

    private String notificationSubject;

    private String reciever;

    private Long customerId;

    private String traceCode;

    private NotificationStatus status;

    private String description;

    private String body;

    private String responseInfo;

    private String requestInfo;


    private String notificationAgentInfoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getConsumerAppId() {
        return consumerAppId;
    }

    public void setConsumerAppId(Long consumerAppId) {
        this.consumerAppId = consumerAppId;
    }

    public String getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getNotificationAgentInfoId() {
        return notificationAgentInfoId;
    }

    public void setNotificationAgentInfoId(String notificationAgentInfoId) {
        this.notificationAgentInfoId = notificationAgentInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationLogDTO notificationLogDTO = (NotificationLogDTO) o;
        if (notificationLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationLogDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", consumerAppId=" + getConsumerAppId() +
            ", notificationSubject='" + getNotificationSubject() + "'" +
            ", reciever='" + getReciever() + "'" +
            ", customerId=" + getCustomerId() +
            ", traceCode='" + getTraceCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", body='" + getBody() + "'" +
            ", responseInfo='" + getResponseInfo() + "'" +
            ", requestInfo='" + getRequestInfo() + "'" +
            ", notificationAgentInfoId=" + getNotificationAgentInfoId() +
            "}";
    }
}
