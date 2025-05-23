package com.anytimers.api.domain.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.anytimers.api.domain.user.controller.dto.UserReadDto;
import com.anytimers.api.domain.user.controller.dto.UserSummaryDto;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.domain.user.data.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "role", ignore = true) //TODO: Maybe fix if i want to implement role management

    User toEntity(UserWriteDto dto);

    UserWriteDto toWriteDto(User user);

    UserReadDto toReadDto(User user);

    User toUser(User user);
    @Mapping(target = "fullName", expression = "java(formatFullName(user))")
    UserSummaryDto toSummaryDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromDto(UserWriteDto dto, @MappingTarget User user);

    
    default String formatFullName(User user) {
        if (user == null) {
            return null;
        }
        StringBuilder fullName = new StringBuilder(user.getFirstName());

        if (user.getPrefix() != null && !user.getPrefix().isEmpty()) {
            fullName.append(" ").append(user.getPrefix());
        }

        fullName.append(" ").append(user.getLastName());
        return fullName.toString();
    }
}
