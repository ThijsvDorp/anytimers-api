package com.anytimers.api.domain.group.controller.dto;

import lombok.Data;

@Data
public class GroupSummaryDto {
    private Integer id;
    private String groupName;
    private String description;
}
