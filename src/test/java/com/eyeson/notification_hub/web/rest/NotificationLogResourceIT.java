package com.eyeson.notification_hub.web.rest;

import com.eyeson.notification_hub.GlobalNotificationHubApp;
import com.eyeson.notification_hub.domain.NotificationLog;
import com.eyeson.notification_hub.repository.NotificationLogRepository;
import com.eyeson.notification_hub.repository.search.NotificationLogSearchRepository;
import com.eyeson.notification_hub.service.NotificationLogService;
import com.eyeson.notification_hub.service.dto.NotificationLogDTO;
import com.eyeson.notification_hub.service.mapper.NotificationLogMapper;
import com.eyeson.notification_hub.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.eyeson.notification_hub.web.rest.TestUtil.sameInstant;
import static com.eyeson.notification_hub.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eyeson.notification_hub.domain.enumeration.NotificationStatus;
/**
 * Integration tests for the {@link NotificationLogResource} REST controller.
 */
@SpringBootTest(classes = GlobalNotificationHubApp.class)
public class NotificationLogResourceIT {

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CONSUMER_APP_ID = 1L;
    private static final Long UPDATED_CONSUMER_APP_ID = 2L;

    private static final String DEFAULT_NOTIFICATION_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_RECIEVER = "AAAAAAAAAA";
    private static final String UPDATED_RECIEVER = "BBBBBBBBBB";

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_TRACE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRACE_CODE = "BBBBBBBBBB";

    private static final NotificationStatus DEFAULT_STATUS = NotificationStatus.ACTIVE;
    private static final NotificationStatus UPDATED_STATUS = NotificationStatus.FAILED;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_INFO = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_INFO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_INFO = "BBBBBBBBBB";

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Autowired
    private NotificationLogMapper notificationLogMapper;

    @Autowired
    private NotificationLogService notificationLogService;

    /**
     * This repository is mocked in the com.eyeson.notification_hub.repository.search test package.
     *
     * @see com.eyeson.notification_hub.repository.search.NotificationLogSearchRepositoryMockConfiguration
     */
    @Autowired
    private NotificationLogSearchRepository mockNotificationLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNotificationLogMockMvc;

    private NotificationLog notificationLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationLogResource notificationLogResource = new NotificationLogResource(notificationLogService);
        this.restNotificationLogMockMvc = MockMvcBuilders.standaloneSetup(notificationLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationLog createEntity() {
        NotificationLog notificationLog = new NotificationLog()
            .createDate(DEFAULT_CREATE_DATE)
            .consumerAppId(DEFAULT_CONSUMER_APP_ID)
            .notificationSubject(DEFAULT_NOTIFICATION_SUBJECT)
            .reciever(DEFAULT_RECIEVER)
            .customerId(DEFAULT_CUSTOMER_ID)
            .traceCode(DEFAULT_TRACE_CODE)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .body(DEFAULT_BODY)
            .responseInfo(DEFAULT_RESPONSE_INFO)
            .requestInfo(DEFAULT_REQUEST_INFO);
        return notificationLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationLog createUpdatedEntity() {
        NotificationLog notificationLog = new NotificationLog()
            .createDate(UPDATED_CREATE_DATE)
            .consumerAppId(UPDATED_CONSUMER_APP_ID)
            .notificationSubject(UPDATED_NOTIFICATION_SUBJECT)
            .reciever(UPDATED_RECIEVER)
            .customerId(UPDATED_CUSTOMER_ID)
            .traceCode(UPDATED_TRACE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .body(UPDATED_BODY)
            .responseInfo(UPDATED_RESPONSE_INFO)
            .requestInfo(UPDATED_REQUEST_INFO);
        return notificationLog;
    }

    @BeforeEach
    public void initTest() {
        notificationLogRepository.deleteAll();
        notificationLog = createEntity();
    }

    @Test
    public void createNotificationLog() throws Exception {
        int databaseSizeBeforeCreate = notificationLogRepository.findAll().size();

        // Create the NotificationLog
        NotificationLogDTO notificationLogDTO = notificationLogMapper.toDto(notificationLog);
        restNotificationLogMockMvc.perform(post("/api/notification-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationLogDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationLog in the database
        List<NotificationLog> notificationLogList = notificationLogRepository.findAll();
        assertThat(notificationLogList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationLog testNotificationLog = notificationLogList.get(notificationLogList.size() - 1);
        assertThat(testNotificationLog.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testNotificationLog.getConsumerAppId()).isEqualTo(DEFAULT_CONSUMER_APP_ID);
        assertThat(testNotificationLog.getNotificationSubject()).isEqualTo(DEFAULT_NOTIFICATION_SUBJECT);
        assertThat(testNotificationLog.getReciever()).isEqualTo(DEFAULT_RECIEVER);
        assertThat(testNotificationLog.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testNotificationLog.getTraceCode()).isEqualTo(DEFAULT_TRACE_CODE);
        assertThat(testNotificationLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNotificationLog.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNotificationLog.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testNotificationLog.getResponseInfo()).isEqualTo(DEFAULT_RESPONSE_INFO);
        assertThat(testNotificationLog.getRequestInfo()).isEqualTo(DEFAULT_REQUEST_INFO);

        // Validate the NotificationLog in Elasticsearch
        verify(mockNotificationLogSearchRepository, times(1)).save(testNotificationLog);
    }

    @Test
    public void createNotificationLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationLogRepository.findAll().size();

        // Create the NotificationLog with an existing ID
        notificationLog.setId("existing_id");
        NotificationLogDTO notificationLogDTO = notificationLogMapper.toDto(notificationLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationLogMockMvc.perform(post("/api/notification-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationLog in the database
        List<NotificationLog> notificationLogList = notificationLogRepository.findAll();
        assertThat(notificationLogList).hasSize(databaseSizeBeforeCreate);

        // Validate the NotificationLog in Elasticsearch
        verify(mockNotificationLogSearchRepository, times(0)).save(notificationLog);
    }


    @Test
    public void getAllNotificationLogs() throws Exception {
        // Initialize the database
        notificationLogRepository.save(notificationLog);

        // Get all the notificationLogList
        restNotificationLogMockMvc.perform(get("/api/notification-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationLog.getId())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].consumerAppId").value(hasItem(DEFAULT_CONSUMER_APP_ID.intValue())))
            .andExpect(jsonPath("$.[*].notificationSubject").value(hasItem(DEFAULT_NOTIFICATION_SUBJECT)))
            .andExpect(jsonPath("$.[*].reciever").value(hasItem(DEFAULT_RECIEVER)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].traceCode").value(hasItem(DEFAULT_TRACE_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY)))
            .andExpect(jsonPath("$.[*].responseInfo").value(hasItem(DEFAULT_RESPONSE_INFO)))
            .andExpect(jsonPath("$.[*].requestInfo").value(hasItem(DEFAULT_REQUEST_INFO)));
    }
    
    @Test
    public void getNotificationLog() throws Exception {
        // Initialize the database
        notificationLogRepository.save(notificationLog);

        // Get the notificationLog
        restNotificationLogMockMvc.perform(get("/api/notification-logs/{id}", notificationLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationLog.getId()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.consumerAppId").value(DEFAULT_CONSUMER_APP_ID.intValue()))
            .andExpect(jsonPath("$.notificationSubject").value(DEFAULT_NOTIFICATION_SUBJECT))
            .andExpect(jsonPath("$.reciever").value(DEFAULT_RECIEVER))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.traceCode").value(DEFAULT_TRACE_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY))
            .andExpect(jsonPath("$.responseInfo").value(DEFAULT_RESPONSE_INFO))
            .andExpect(jsonPath("$.requestInfo").value(DEFAULT_REQUEST_INFO));
    }

    @Test
    public void getNonExistingNotificationLog() throws Exception {
        // Get the notificationLog
        restNotificationLogMockMvc.perform(get("/api/notification-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNotificationLog() throws Exception {
        // Initialize the database
        notificationLogRepository.save(notificationLog);

        int databaseSizeBeforeUpdate = notificationLogRepository.findAll().size();

        // Update the notificationLog
        NotificationLog updatedNotificationLog = notificationLogRepository.findById(notificationLog.getId()).get();
        updatedNotificationLog
            .createDate(UPDATED_CREATE_DATE)
            .consumerAppId(UPDATED_CONSUMER_APP_ID)
            .notificationSubject(UPDATED_NOTIFICATION_SUBJECT)
            .reciever(UPDATED_RECIEVER)
            .customerId(UPDATED_CUSTOMER_ID)
            .traceCode(UPDATED_TRACE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .body(UPDATED_BODY)
            .responseInfo(UPDATED_RESPONSE_INFO)
            .requestInfo(UPDATED_REQUEST_INFO);
        NotificationLogDTO notificationLogDTO = notificationLogMapper.toDto(updatedNotificationLog);

        restNotificationLogMockMvc.perform(put("/api/notification-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationLogDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationLog in the database
        List<NotificationLog> notificationLogList = notificationLogRepository.findAll();
        assertThat(notificationLogList).hasSize(databaseSizeBeforeUpdate);
        NotificationLog testNotificationLog = notificationLogList.get(notificationLogList.size() - 1);
        assertThat(testNotificationLog.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testNotificationLog.getConsumerAppId()).isEqualTo(UPDATED_CONSUMER_APP_ID);
        assertThat(testNotificationLog.getNotificationSubject()).isEqualTo(UPDATED_NOTIFICATION_SUBJECT);
        assertThat(testNotificationLog.getReciever()).isEqualTo(UPDATED_RECIEVER);
        assertThat(testNotificationLog.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testNotificationLog.getTraceCode()).isEqualTo(UPDATED_TRACE_CODE);
        assertThat(testNotificationLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNotificationLog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNotificationLog.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testNotificationLog.getResponseInfo()).isEqualTo(UPDATED_RESPONSE_INFO);
        assertThat(testNotificationLog.getRequestInfo()).isEqualTo(UPDATED_REQUEST_INFO);

        // Validate the NotificationLog in Elasticsearch
        verify(mockNotificationLogSearchRepository, times(1)).save(testNotificationLog);
    }

    @Test
    public void updateNonExistingNotificationLog() throws Exception {
        int databaseSizeBeforeUpdate = notificationLogRepository.findAll().size();

        // Create the NotificationLog
        NotificationLogDTO notificationLogDTO = notificationLogMapper.toDto(notificationLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationLogMockMvc.perform(put("/api/notification-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationLog in the database
        List<NotificationLog> notificationLogList = notificationLogRepository.findAll();
        assertThat(notificationLogList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NotificationLog in Elasticsearch
        verify(mockNotificationLogSearchRepository, times(0)).save(notificationLog);
    }

    @Test
    public void deleteNotificationLog() throws Exception {
        // Initialize the database
        notificationLogRepository.save(notificationLog);

        int databaseSizeBeforeDelete = notificationLogRepository.findAll().size();

        // Delete the notificationLog
        restNotificationLogMockMvc.perform(delete("/api/notification-logs/{id}", notificationLog.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationLog> notificationLogList = notificationLogRepository.findAll();
        assertThat(notificationLogList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NotificationLog in Elasticsearch
        verify(mockNotificationLogSearchRepository, times(1)).deleteById(notificationLog.getId());
    }

    @Test
    public void searchNotificationLog() throws Exception {
        // Initialize the database
        notificationLogRepository.save(notificationLog);
        when(mockNotificationLogSearchRepository.search(queryStringQuery("id:" + notificationLog.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(notificationLog), PageRequest.of(0, 1), 1));
        // Search the notificationLog
        restNotificationLogMockMvc.perform(get("/api/_search/notification-logs?query=id:" + notificationLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationLog.getId())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].consumerAppId").value(hasItem(DEFAULT_CONSUMER_APP_ID.intValue())))
            .andExpect(jsonPath("$.[*].notificationSubject").value(hasItem(DEFAULT_NOTIFICATION_SUBJECT)))
            .andExpect(jsonPath("$.[*].reciever").value(hasItem(DEFAULT_RECIEVER)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].traceCode").value(hasItem(DEFAULT_TRACE_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY)))
            .andExpect(jsonPath("$.[*].responseInfo").value(hasItem(DEFAULT_RESPONSE_INFO)))
            .andExpect(jsonPath("$.[*].requestInfo").value(hasItem(DEFAULT_REQUEST_INFO)));
    }
}
