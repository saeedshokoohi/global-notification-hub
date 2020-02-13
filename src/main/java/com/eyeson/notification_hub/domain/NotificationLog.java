package com.eyeson.notification_hub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.ZonedDateTime;

import com.eyeson.notification_hub.domain.enumeration.NotificationStatus;

/**
 * A NotificationLog.
 */
@Document(collection = "notification_log")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notificationlog")
public class NotificationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("create_date")
    private ZonedDateTime createDate;

    @Field("consumer_app_id")
    private Long consumerAppId;

    @Field("notification_subject")
    private String notificationSubject;

    @Field("reciever")
    private String reciever;

    @Field("customer_id")
    private Long customerId;

    @Field("trace_code")
    private String traceCode;

    @Field("status")
    private NotificationStatus status;

    @Field("description")
    private String description;

    @Field("body")
    private String body;

    @Field("response_info")
    private String responseInfo;

    @Field("request_info")
    private String requestInfo;

    @DBRef
    @Field("notificationAgentInfo")
    @JsonIgnoreProperties("notificationLogs")
    private NotificationAgentInfo notificationAgentInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NotificationLog createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getConsumerAppId() {
        return consumerAppId;
    }

    public NotificationLog consumerAppId(Long consumerAppId) {
        this.consumerAppId = consumerAppId;
        return this;
    }

    public void setConsumerAppId(Long consumerAppId) {
        this.consumerAppId = consumerAppId;
    }

    public String getNotificationSubject() {
        return notificationSubject;
    }

    public NotificationLog notificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
        return this;
    }

    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public String getReciever() {
        return reciever;
    }

    public NotificationLog reciever(String reciever) {
        this.reciever = reciever;
        return this;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public NotificationLog customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public NotificationLog traceCode(String traceCode) {
        this.traceCode = traceCode;
        return this;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public NotificationLog status(NotificationStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public NotificationLog description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public NotificationLog body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public NotificationLog responseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
        return this;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public NotificationLog requestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
        return this;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public NotificationAgentInfo getNotificationAgentInfo() {
        return notificationAgentInfo;
    }

    public NotificationLog notificationAgentInfo(NotificationAgentInfo notificationAgentInfo) {
        this.notificationAgentInfo = notificationAgentInfo;
        return this;
    }

    public void setNotificationAgentInfo(NotificationAgentInfo notificationAgentInfo) {
        this.notificationAgentInfo = notificationAgentInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationLog)) {
            return false;
        }
        return id != null && id.equals(((NotificationLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationLog{" +
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
            "}";
    }
}
