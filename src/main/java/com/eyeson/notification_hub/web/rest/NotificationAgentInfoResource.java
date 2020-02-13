package com.eyeson.notification_hub.web.rest;

import com.eyeson.notification_hub.service.NotificationAgentInfoService;
import com.eyeson.notification_hub.web.rest.errors.BadRequestAlertException;
import com.eyeson.notification_hub.service.dto.NotificationAgentInfoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.eyeson.notification_hub.domain.NotificationAgentInfo}.
 */
@RestController
@RequestMapping("/api")
public class NotificationAgentInfoResource {

    private final Logger log = LoggerFactory.getLogger(NotificationAgentInfoResource.class);

    private static final String ENTITY_NAME = "globalNotificationHubNotificationAgentInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationAgentInfoService notificationAgentInfoService;

    public NotificationAgentInfoResource(NotificationAgentInfoService notificationAgentInfoService) {
        this.notificationAgentInfoService = notificationAgentInfoService;
    }

    /**
     * {@code POST  /notification-agent-infos} : Create a new notificationAgentInfo.
     *
     * @param notificationAgentInfoDTO the notificationAgentInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationAgentInfoDTO, or with status {@code 400 (Bad Request)} if the notificationAgentInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-agent-infos")
    public ResponseEntity<NotificationAgentInfoDTO> createNotificationAgentInfo(@RequestBody NotificationAgentInfoDTO notificationAgentInfoDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationAgentInfo : {}", notificationAgentInfoDTO);
        if (notificationAgentInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationAgentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationAgentInfoDTO result = notificationAgentInfoService.save(notificationAgentInfoDTO);
        return ResponseEntity.created(new URI("/api/notification-agent-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-agent-infos} : Updates an existing notificationAgentInfo.
     *
     * @param notificationAgentInfoDTO the notificationAgentInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationAgentInfoDTO,
     * or with status {@code 400 (Bad Request)} if the notificationAgentInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationAgentInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-agent-infos")
    public ResponseEntity<NotificationAgentInfoDTO> updateNotificationAgentInfo(@RequestBody NotificationAgentInfoDTO notificationAgentInfoDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationAgentInfo : {}", notificationAgentInfoDTO);
        if (notificationAgentInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationAgentInfoDTO result = notificationAgentInfoService.save(notificationAgentInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationAgentInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-agent-infos} : get all the notificationAgentInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationAgentInfos in body.
     */
    @GetMapping("/notification-agent-infos")
    public ResponseEntity<List<NotificationAgentInfoDTO>> getAllNotificationAgentInfos(Pageable pageable) {
        log.debug("REST request to get a page of NotificationAgentInfos");
        Page<NotificationAgentInfoDTO> page = notificationAgentInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notification-agent-infos/:id} : get the "id" notificationAgentInfo.
     *
     * @param id the id of the notificationAgentInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationAgentInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-agent-infos/{id}")
    public ResponseEntity<NotificationAgentInfoDTO> getNotificationAgentInfo(@PathVariable String id) {
        log.debug("REST request to get NotificationAgentInfo : {}", id);
        Optional<NotificationAgentInfoDTO> notificationAgentInfoDTO = notificationAgentInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationAgentInfoDTO);
    }

    /**
     * {@code DELETE  /notification-agent-infos/:id} : delete the "id" notificationAgentInfo.
     *
     * @param id the id of the notificationAgentInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-agent-infos/{id}")
    public ResponseEntity<Void> deleteNotificationAgentInfo(@PathVariable String id) {
        log.debug("REST request to delete NotificationAgentInfo : {}", id);
        notificationAgentInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/notification-agent-infos?query=:query} : search for the notificationAgentInfo corresponding
     * to the query.
     *
     * @param query the query of the notificationAgentInfo search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/notification-agent-infos")
    public ResponseEntity<List<NotificationAgentInfoDTO>> searchNotificationAgentInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of NotificationAgentInfos for query {}", query);
        Page<NotificationAgentInfoDTO> page = notificationAgentInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
