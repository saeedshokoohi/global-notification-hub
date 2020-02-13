package com.eyeson.notification_hub.service.impl;

import com.eyeson.notification_hub.service.NotificationAgentInfoService;
import com.eyeson.notification_hub.domain.NotificationAgentInfo;
import com.eyeson.notification_hub.repository.NotificationAgentInfoRepository;
import com.eyeson.notification_hub.repository.search.NotificationAgentInfoSearchRepository;
import com.eyeson.notification_hub.service.dto.NotificationAgentInfoDTO;
import com.eyeson.notification_hub.service.mapper.NotificationAgentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link NotificationAgentInfo}.
 */
@Service
public class NotificationAgentInfoServiceImpl implements NotificationAgentInfoService {

    private final Logger log = LoggerFactory.getLogger(NotificationAgentInfoServiceImpl.class);

    private final NotificationAgentInfoRepository notificationAgentInfoRepository;

    private final NotificationAgentInfoMapper notificationAgentInfoMapper;

    private final NotificationAgentInfoSearchRepository notificationAgentInfoSearchRepository;

    public NotificationAgentInfoServiceImpl(NotificationAgentInfoRepository notificationAgentInfoRepository, NotificationAgentInfoMapper notificationAgentInfoMapper, NotificationAgentInfoSearchRepository notificationAgentInfoSearchRepository) {
        this.notificationAgentInfoRepository = notificationAgentInfoRepository;
        this.notificationAgentInfoMapper = notificationAgentInfoMapper;
        this.notificationAgentInfoSearchRepository = notificationAgentInfoSearchRepository;
    }

    /**
     * Save a notificationAgentInfo.
     *
     * @param notificationAgentInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationAgentInfoDTO save(NotificationAgentInfoDTO notificationAgentInfoDTO) {
        log.debug("Request to save NotificationAgentInfo : {}", notificationAgentInfoDTO);
        NotificationAgentInfo notificationAgentInfo = notificationAgentInfoMapper.toEntity(notificationAgentInfoDTO);
        notificationAgentInfo = notificationAgentInfoRepository.save(notificationAgentInfo);
        NotificationAgentInfoDTO result = notificationAgentInfoMapper.toDto(notificationAgentInfo);
        notificationAgentInfoSearchRepository.save(notificationAgentInfo);
        return result;
    }

    /**
     * Get all the notificationAgentInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NotificationAgentInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationAgentInfos");
        return notificationAgentInfoRepository.findAll(pageable)
            .map(notificationAgentInfoMapper::toDto);
    }

    /**
     * Get one notificationAgentInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<NotificationAgentInfoDTO> findOne(String id) {
        log.debug("Request to get NotificationAgentInfo : {}", id);
        return notificationAgentInfoRepository.findById(id)
            .map(notificationAgentInfoMapper::toDto);
    }

    /**
     * Delete the notificationAgentInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete NotificationAgentInfo : {}", id);
        notificationAgentInfoRepository.deleteById(id);
        notificationAgentInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the notificationAgentInfo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NotificationAgentInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of NotificationAgentInfos for query {}", query);
        return notificationAgentInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(notificationAgentInfoMapper::toDto);
    }
}
