package com.eyeson.notification_hub.web.rest;

import com.eyeson.notification_hub.GlobalNotificationHubApp;
import com.eyeson.notification_hub.domain.NotificationAgentInfo;
import com.eyeson.notification_hub.repository.NotificationAgentInfoRepository;
import com.eyeson.notification_hub.repository.search.NotificationAgentInfoSearchRepository;
import com.eyeson.notification_hub.service.NotificationAgentInfoService;
import com.eyeson.notification_hub.service.dto.NotificationAgentInfoDTO;
import com.eyeson.notification_hub.service.mapper.NotificationAgentInfoMapper;
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

import com.eyeson.notification_hub.domain.enumeration.NotificationType;
import com.eyeson.notification_hub.domain.enumeration.NotificationCenter;
import com.eyeson.notification_hub.domain.enumeration.NotificationAgentStatus;
/**
 * Integration tests for the {@link NotificationAgentInfoResource} REST controller.
 */
@SpringBootTest(classes = GlobalNotificationHubApp.class)
public class NotificationAgentInfoResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final NotificationType DEFAULT_NOTIFICATION_TYPE = NotificationType.SMS;
    private static final NotificationType UPDATED_NOTIFICATION_TYPE = NotificationType.EMAIL;

    private static final NotificationCenter DEFAULT_NOTIFICATION_CENTER = NotificationCenter.MELLIPAYAMAK;
    private static final NotificationCenter UPDATED_NOTIFICATION_CENTER = NotificationCenter.IRANSMS;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final NotificationAgentStatus DEFAULT_STATUS = NotificationAgentStatus.ACTIVE;
    private static final NotificationAgentStatus UPDATED_STATUS = NotificationAgentStatus.EXPIRED;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_CONFIG = "BBBBBBBBBB";

    @Autowired
    private NotificationAgentInfoRepository notificationAgentInfoRepository;

    @Autowired
    private NotificationAgentInfoMapper notificationAgentInfoMapper;

    @Autowired
    private NotificationAgentInfoService notificationAgentInfoService;

    /**
     * This repository is mocked in the com.eyeson.notification_hub.repository.search test package.
     *
     * @see com.eyeson.notification_hub.repository.search.NotificationAgentInfoSearchRepositoryMockConfiguration
     */
    @Autowired
    private NotificationAgentInfoSearchRepository mockNotificationAgentInfoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNotificationAgentInfoMockMvc;

    private NotificationAgentInfo notificationAgentInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationAgentInfoResource notificationAgentInfoResource = new NotificationAgentInfoResource(notificationAgentInfoService);
        this.restNotificationAgentInfoMockMvc = MockMvcBuilders.standaloneSetup(notificationAgentInfoResource)
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
    public static NotificationAgentInfo createEntity() {
        NotificationAgentInfo notificationAgentInfo = new NotificationAgentInfo()
            .title(DEFAULT_TITLE)
            .createDate(DEFAULT_CREATE_DATE)
            .notificationType(DEFAULT_NOTIFICATION_TYPE)
            .notificationCenter(DEFAULT_NOTIFICATION_CENTER)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .customerId(DEFAULT_CUSTOMER_ID)
            .token(DEFAULT_TOKEN)
            .sender(DEFAULT_SENDER)
            .status(DEFAULT_STATUS)
            .isDeleted(DEFAULT_IS_DELETED)
            .description(DEFAULT_DESCRIPTION)
            .agentConfig(DEFAULT_AGENT_CONFIG);
        return notificationAgentInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationAgentInfo createUpdatedEntity() {
        NotificationAgentInfo notificationAgentInfo = new NotificationAgentInfo()
            .title(UPDATED_TITLE)
            .createDate(UPDATED_CREATE_DATE)
            .notificationType(UPDATED_NOTIFICATION_TYPE)
            .notificationCenter(UPDATED_NOTIFICATION_CENTER)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .customerId(UPDATED_CUSTOMER_ID)
            .token(UPDATED_TOKEN)
            .sender(UPDATED_SENDER)
            .status(UPDATED_STATUS)
            .isDeleted(UPDATED_IS_DELETED)
            .description(UPDATED_DESCRIPTION)
            .agentConfig(UPDATED_AGENT_CONFIG);
        return notificationAgentInfo;
    }

    @BeforeEach
    public void initTest() {
        notificationAgentInfoRepository.deleteAll();
        notificationAgentInfo = createEntity();
    }

    @Test
    public void createNotificationAgentInfo() throws Exception {
        int databaseSizeBeforeCreate = notificationAgentInfoRepository.findAll().size();

        // Create the NotificationAgentInfo
        NotificationAgentInfoDTO notificationAgentInfoDTO = notificationAgentInfoMapper.toDto(notificationAgentInfo);
        restNotificationAgentInfoMockMvc.perform(post("/api/notification-agent-infos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationAgentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationAgentInfo in the database
        List<NotificationAgentInfo> notificationAgentInfoList = notificationAgentInfoRepository.findAll();
        assertThat(notificationAgentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationAgentInfo testNotificationAgentInfo = notificationAgentInfoList.get(notificationAgentInfoList.size() - 1);
        assertThat(testNotificationAgentInfo.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testNotificationAgentInfo.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testNotificationAgentInfo.getNotificationType()).isEqualTo(DEFAULT_NOTIFICATION_TYPE);
        assertThat(testNotificationAgentInfo.getNotificationCenter()).isEqualTo(DEFAULT_NOTIFICATION_CENTER);
        assertThat(testNotificationAgentInfo.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testNotificationAgentInfo.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testNotificationAgentInfo.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testNotificationAgentInfo.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testNotificationAgentInfo.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testNotificationAgentInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNotificationAgentInfo.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testNotificationAgentInfo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNotificationAgentInfo.getAgentConfig()).isEqualTo(DEFAULT_AGENT_CONFIG);

        // Validate the NotificationAgentInfo in Elasticsearch
        verify(mockNotificationAgentInfoSearchRepository, times(1)).save(testNotificationAgentInfo);
    }

    @Test
    public void createNotificationAgentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationAgentInfoRepository.findAll().size();

        // Create the NotificationAgentInfo with an existing ID
        notificationAgentInfo.setId("existing_id");
        NotificationAgentInfoDTO notificationAgentInfoDTO = notificationAgentInfoMapper.toDto(notificationAgentInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationAgentInfoMockMvc.perform(post("/api/notification-agent-infos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationAgentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationAgentInfo in the database
        List<NotificationAgentInfo> notificationAgentInfoList = notificationAgentInfoRepository.findAll();
        assertThat(notificationAgentInfoList).hasSize(databaseSizeBeforeCreate);

        // Validate the NotificationAgentInfo in Elasticsearch
        verify(mockNotificationAgentInfoSearchRepository, times(0)).save(notificationAgentInfo);
    }


    @Test
    public void getAllNotificationAgentInfos() throws Exception {
        // Initialize the database
        notificationAgentInfoRepository.save(notificationAgentInfo);

        // Get all the notificationAgentInfoList
        restNotificationAgentInfoMockMvc.perform(get("/api/notification-agent-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationAgentInfo.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].notificationType").value(hasItem(DEFAULT_NOTIFICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].notificationCenter").value(hasItem(DEFAULT_NOTIFICATION_CENTER.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].agentConfig").value(hasItem(DEFAULT_AGENT_CONFIG)));
    }
    
    @Test
    public void getNotificationAgentInfo() throws Exception {
        // Initialize the database
        notificationAgentInfoRepository.save(notificationAgentInfo);

        // Get the notificationAgentInfo
        restNotificationAgentInfoMockMvc.perform(get("/api/notification-agent-infos/{id}", notificationAgentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationAgentInfo.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.notificationType").value(DEFAULT_NOTIFICATION_TYPE.toString()))
            .andExpect(jsonPath("$.notificationCenter").value(DEFAULT_NOTIFICATION_CENTER.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.agentConfig").value(DEFAULT_AGENT_CONFIG));
    }

    @Test
    public void getNonExistingNotificationAgentInfo() throws Exception {
        // Get the notificationAgentInfo
        restNotificationAgentInfoMockMvc.perform(get("/api/notification-agent-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNotificationAgentInfo() throws Exception {
        // Initialize the database
        notificationAgentInfoRepository.save(notificationAgentInfo);

        int databaseSizeBeforeUpdate = notificationAgentInfoRepository.findAll().size();

        // Update the notificationAgentInfo
        NotificationAgentInfo updatedNotificationAgentInfo = notificationAgentInfoRepository.findById(notificationAgentInfo.getId()).get();
        updatedNotificationAgentInfo
            .title(UPDATED_TITLE)
            .createDate(UPDATED_CREATE_DATE)
            .notificationType(UPDATED_NOTIFICATION_TYPE)
            .notificationCenter(UPDATED_NOTIFICATION_CENTER)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .customerId(UPDATED_CUSTOMER_ID)
            .token(UPDATED_TOKEN)
            .sender(UPDATED_SENDER)
            .status(UPDATED_STATUS)
            .isDeleted(UPDATED_IS_DELETED)
            .description(UPDATED_DESCRIPTION)
            .agentConfig(UPDATED_AGENT_CONFIG);
        NotificationAgentInfoDTO notificationAgentInfoDTO = notificationAgentInfoMapper.toDto(updatedNotificationAgentInfo);

        restNotificationAgentInfoMockMvc.perform(put("/api/notification-agent-infos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationAgentInfoDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationAgentInfo in the database
        List<NotificationAgentInfo> notificationAgentInfoList = notificationAgentInfoRepository.findAll();
        assertThat(notificationAgentInfoList).hasSize(databaseSizeBeforeUpdate);
        NotificationAgentInfo testNotificationAgentInfo = notificationAgentInfoList.get(notificationAgentInfoList.size() - 1);
        assertThat(testNotificationAgentInfo.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testNotificationAgentInfo.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testNotificationAgentInfo.getNotificationType()).isEqualTo(UPDATED_NOTIFICATION_TYPE);
        assertThat(testNotificationAgentInfo.getNotificationCenter()).isEqualTo(UPDATED_NOTIFICATION_CENTER);
        assertThat(testNotificationAgentInfo.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testNotificationAgentInfo.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testNotificationAgentInfo.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testNotificationAgentInfo.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testNotificationAgentInfo.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testNotificationAgentInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNotificationAgentInfo.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testNotificationAgentInfo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNotificationAgentInfo.getAgentConfig()).isEqualTo(UPDATED_AGENT_CONFIG);

        // Validate the NotificationAgentInfo in Elasticsearch
        verify(mockNotificationAgentInfoSearchRepository, times(1)).save(testNotificationAgentInfo);
    }

    @Test
    public void updateNonExistingNotificationAgentInfo() throws Exception {
        int databaseSizeBeforeUpdate = notificationAgentInfoRepository.findAll().size();

        // Create the NotificationAgentInfo
        NotificationAgentInfoDTO notificationAgentInfoDTO = notificationAgentInfoMapper.toDto(notificationAgentInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationAgentInfoMockMvc.perform(put("/api/notification-agent-infos")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationAgentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationAgentInfo in the database
        List<NotificationAgentInfo> notificationAgentInfoList = notificationAgentInfoRepository.findAll();
        assertThat(notificationAgentInfoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NotificationAgentInfo in Elasticsearch
        verify(mockNotificationAgentInfoSearchRepository, times(0)).save(notificationAgentInfo);
    }

    @Test
    public void deleteNotificationAgentInfo() throws Exception {
        // Initialize the database
        notificationAgentInfoRepository.save(notificationAgentInfo);

        int databaseSizeBeforeDelete = notificationAgentInfoRepository.findAll().size();

        // Delete the notificationAgentInfo
        restNotificationAgentInfoMockMvc.perform(delete("/api/notification-agent-infos/{id}", notificationAgentInfo.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationAgentInfo> notificationAgentInfoList = notificationAgentInfoRepository.findAll();
        assertThat(notificationAgentInfoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NotificationAgentInfo in Elasticsearch
        verify(mockNotificationAgentInfoSearchRepository, times(1)).deleteById(notificationAgentInfo.getId());
    }

    @Test
    public void searchNotificationAgentInfo() throws Exception {
        // Initialize the database
        notificationAgentInfoRepository.save(notificationAgentInfo);
        when(mockNotificationAgentInfoSearchRepository.search(queryStringQuery("id:" + notificationAgentInfo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(notificationAgentInfo), PageRequest.of(0, 1), 1));
        // Search the notificationAgentInfo
        restNotificationAgentInfoMockMvc.perform(get("/api/_search/notification-agent-infos?query=id:" + notificationAgentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationAgentInfo.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].notificationType").value(hasItem(DEFAULT_NOTIFICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].notificationCenter").value(hasItem(DEFAULT_NOTIFICATION_CENTER.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].agentConfig").value(hasItem(DEFAULT_AGENT_CONFIG)));
    }
}
