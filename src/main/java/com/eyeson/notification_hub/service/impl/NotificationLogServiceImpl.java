package com.eyeson.notification_hub.service.impl;

import com.eyeson.notification_hub.service.NotificationLogService;
import com.eyeson.notification_hub.domain.NotificationLog;
import com.eyeson.notification_hub.repository.NotificationLogRepository;
import com.eyeson.notification_hub.repository.search.NotificationLogSearchRepository;
import com.eyeson.notification_hub.service.dto.NotificationLogDTO;
import com.eyeson.notification_hub.service.mapper.NotificationLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link NotificationLog}.
 */
@Service
public class NotificationLogServiceImpl implements NotificationLogService {

    private final Logger log = LoggerFactory.getLogger(NotificationLogServiceImpl.class);

    private final NotificationLogRepository notificationLogRepository;

    private final NotificationLogMapper notificationLogMapper;

    private final NotificationLogSearchRepository notificationLogSearchRepository;

    public NotificationLogServiceImpl(NotificationLogRepository notificationLogRepository, NotificationLogMapper notificationLogMapper, NotificationLogSearchRepository notificationLogSearchRepository) {
        this.notificationLogRepository = notificationLogRepository;
        this.notificationLogMapper = notificationLogMapper;
        this.notificationLogSearchRepository = notificationLogSearchRepository;
    }

    /**
     * Save a notificationLog.
     *
     * @param notificationLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationLogDTO save(NotificationLogDTO notificationLogDTO) {
        log.debug("Request to save NotificationLog : {}", notificationLogDTO);
        NotificationLog notificationLog = notificationLogMapper.toEntity(notificationLogDTO);
        notificationLog = notificationLogRepository.save(notificationLog);
        NotificationLogDTO result = notificationLogMapper.toDto(notificationLog);
        notificationLogSearchRepository.save(notificationLog);
        return result;
    }

    /**
     * Get all the notificationLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NotificationLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationLogs");
        return notificationLogRepository.findAll(pageable)
            .map(notificationLogMapper::toDto);
    }

    /**
     * Get one notificationLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<NotificationLogDTO> findOne(String id) {
        log.debug("Request to get NotificationLog : {}", id);
        return notificationLogRepository.findById(id)
            .map(notificationLogMapper::toDto);
    }

    /**
     * Delete the notificationLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete NotificationLog : {}", id);
        notificationLogRepository.deleteById(id);
        notificationLogSearchRepository.deleteById(id);
    }

    /**
     * Search for the notificationLog corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NotificationLogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of NotificationLogs for query {}", query);
        return notificationLogSearchRepository.search(queryStringQuery(query), pageable)
            .map(notificationLogMapper::toDto);
    }
}
