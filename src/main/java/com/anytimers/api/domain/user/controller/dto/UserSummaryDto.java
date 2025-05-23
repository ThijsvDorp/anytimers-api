package com.anytimers.api.domain.user.controller.dto;

import lombok.Data;

@Data
public class UserSummaryDto {

    private Integer id;

    private String userName;
    
    private String email;

    private String fullName;
}
