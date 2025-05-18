package com.anytimers.api.domain.group.controller.dto;

import java.time.Instant;
import java.util.Set;


import lombok.Data;

@Data
public class GroupReadDto {
    
    private final Integer id;

    private final String name;

    private final String description;

    private final Set<Integer> userIds;

    private Instant createdOn;

    private Instant updatedOn;
}
