package com.anytimers.api.domain.auth.mapper;

import org.mapstruct.factory.Mappers;


public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);
    
}
