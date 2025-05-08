package com.anytimers.api.domain.auth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthReadDto {
    private String accessToken;
    private String refreshToken;
}
