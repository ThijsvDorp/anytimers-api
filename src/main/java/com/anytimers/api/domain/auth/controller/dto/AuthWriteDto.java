package com.anytimers.api.domain.auth.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthWriteDto {

    @NotBlank
    private String identifier;
    
    @NotBlank
    private String password;
}
