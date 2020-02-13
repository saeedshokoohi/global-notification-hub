package com.eyeson.notification_hub.service.mapper;


import com.eyeson.notification_hub.domain.*;
import com.eyeson.notification_hub.service.dto.NotificationAgentInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationAgentInfo} and its DTO {@link NotificationAgentInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificationAgentInfoMapper extends EntityMapper<NotificationAgentInfoDTO, NotificationAgentInfo> {



    default NotificationAgentInfo fromId(String id) {
        if (id == null) {
            return null;
        }
        NotificationAgentInfo notificationAgentInfo = new NotificationAgentInfo();
        notificationAgentInfo.setId(id);
        return notificationAgentInfo;
    }
}
