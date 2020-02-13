package com.eyeson.notification_hub.service;

import com.eyeson.notification_hub.service.dto.NotificationLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eyeson.notification_hub.domain.NotificationLog}.
 */
public interface NotificationLogService {

    /**
     * Save a notificationLog.
     *
     * @param notificationLogDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationLogDTO save(NotificationLogDTO notificationLogDTO);

    /**
     * Get all the notificationLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NotificationLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" notificationLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationLogDTO> findOne(String id);

    /**
     * Delete the "id" notificationLog.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the notificationLog corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NotificationLogDTO> search(String query, Pageable pageable);
}
