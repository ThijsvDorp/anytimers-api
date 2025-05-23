package com.anytimers.api.domain.group.controller.dto;

import java.time.Instant;
import java.util.Set;

import com.anytimers.api.domain.user.controller.dto.UserSummaryDto;

import lombok.Data;

@Data
public class GroupReadDto {
    
    private final Integer id;

    private final String groupName;

    private final String description;

    private final Set<UserSummaryDto> users;

    private Instant createdOn;

    private Instant updatedOn;
}
