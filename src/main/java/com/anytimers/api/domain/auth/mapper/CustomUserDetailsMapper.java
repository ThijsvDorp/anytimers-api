package com.anytimers.api.domain.auth.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anytimers.api.domain.auth.userdetails.CustomUserDetails;
import com.anytimers.api.domain.user.data.User;

@Mapper(componentModel = "spring")
public interface CustomUserDetailsMapper {
    CustomUserDetailsMapper INSTANCE = Mappers.getMapper(CustomUserDetailsMapper.class);

    @Mapping(source = "userName",target = "username")
    @Mapping(target = "authorities", expression = "java(mapRoleToAuthorities(user.getRole().name()))")
    CustomUserDetails toCustomUserDetails(User user);
    
    default List<GrantedAuthority> mapRoleToAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
