package com.eyeson.notification_hub.service;

import com.eyeson.notification_hub.service.dto.NotificationAgentInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eyeson.notification_hub.domain.NotificationAgentInfo}.
 */
public interface NotificationAgentInfoService {

    /**
     * Save a notificationAgentInfo.
     *
     * @param notificationAgentInfoDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationAgentInfoDTO save(NotificationAgentInfoDTO notificationAgentInfoDTO);

    /**
     * Get all the notificationAgentInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NotificationAgentInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" notificationAgentInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationAgentInfoDTO> findOne(String id);

    /**
     * Delete the "id" notificationAgentInfo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the notificationAgentInfo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NotificationAgentInfoDTO> search(String query, Pageable pageable);
}
