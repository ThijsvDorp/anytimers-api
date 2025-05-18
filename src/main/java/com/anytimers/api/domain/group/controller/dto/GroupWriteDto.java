package com.anytimers.api.domain.group.controller.dto;

import java.util.Set;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Create dto for group(s)
 */
@Data
public class GroupWriteDto {

    @NotBlank(message = "Name is required")
    private String groupName;

    @Size(max = 255)
    private String description;

    @Nullable
    private Set<Integer> userIds;
}
