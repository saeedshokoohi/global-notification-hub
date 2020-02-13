package com.eyeson.notification_hub.service.mapper;


import com.eyeson.notification_hub.domain.*;
import com.eyeson.notification_hub.service.dto.NotificationLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationLog} and its DTO {@link NotificationLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {NotificationAgentInfoMapper.class})
public interface NotificationLogMapper extends EntityMapper<NotificationLogDTO, NotificationLog> {

    @Mapping(source = "notificationAgentInfo.id", target = "notificationAgentInfoId")
    NotificationLogDTO toDto(NotificationLog notificationLog);

    @Mapping(source = "notificationAgentInfoId", target = "notificationAgentInfo")
    NotificationLog toEntity(NotificationLogDTO notificationLogDTO);

    default NotificationLog fromId(String id) {
        if (id == null) {
            return null;
        }
        NotificationLog notificationLog = new NotificationLog();
        notificationLog.setId(id);
        return notificationLog;
    }
}
