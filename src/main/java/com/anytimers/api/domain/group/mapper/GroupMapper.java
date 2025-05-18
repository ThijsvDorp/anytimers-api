package com.anytimers.api.domain.group.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.anytimers.api.domain.group.controller.dto.GroupReadDto;
import com.anytimers.api.domain.group.controller.dto.GroupWriteDto;
import com.anytimers.api.domain.group.data.Group;
import com.anytimers.api.domain.user.service.UserService;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface GroupMapper {
    
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "userIds", target = "users")
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    Group toEntity(GroupWriteDto dto);

    GroupWriteDto toWriteDto(Group group);

    GroupReadDto toReadDto(Group group);

    Group toGroup(Group group);
}
