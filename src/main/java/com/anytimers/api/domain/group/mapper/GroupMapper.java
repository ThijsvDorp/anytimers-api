package com.anytimers.api.domain.group.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.anytimers.api.domain.group.controller.dto.GroupReadDto;
import com.anytimers.api.domain.group.controller.dto.GroupWriteDto;
import com.anytimers.api.domain.group.data.Group;
import com.anytimers.api.domain.user.data.User;
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

    @Mapping(source = "users", target = "userIds")
    GroupReadDto toReadDto(Group group);

    Group toGroup(Group group);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Group updateGroupFromDto(GroupWriteDto dto, @MappingTarget Group group);

    default Set<Integer> mapUsersToUserIds(Set<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
            .map(User::getId)
            .collect(Collectors.toSet());
    }
}
