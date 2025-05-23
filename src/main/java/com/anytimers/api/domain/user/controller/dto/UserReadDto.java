package com.anytimers.api.domain.user.controller.dto;

import java.time.Instant;
import java.util.Set;

import com.anytimers.api.domain.group.controller.dto.GroupSummaryDto;
import com.anytimers.api.domain.user.data.Role;

import lombok.Data;

@Data
public class UserReadDto {

    private final Integer id;

    private final String email;

    private final String userName;

    private final String firstName;

    private final String prefix;

    private final String lastName;

    private final Role role; 

    private final Set<GroupSummaryDto> groups;

    private final Instant createdOn;

    private final Instant updatedOn;

}
