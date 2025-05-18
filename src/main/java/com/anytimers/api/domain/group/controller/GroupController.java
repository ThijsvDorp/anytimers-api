package com.anytimers.api.domain.group.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anytimers.api.domain.group.controller.dto.GroupReadDto;
import com.anytimers.api.domain.group.controller.dto.GroupWriteDto;
import com.anytimers.api.domain.group.mapper.GroupMapper;
import com.anytimers.api.domain.group.service.GroupService;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.util.authorities.annotations.IsAdmin;
import com.anytimers.api.util.authorities.annotations.IsGroupOwner;
import com.anytimers.api.util.authorities.annotations.IsUser;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(
    path = "/v1/group",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class GroupController {

    private final GroupService groupService;

    private final GroupMapper groupMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @IsUser
    @PostMapping("/")
    public GroupReadDto createGroup(GroupWriteDto dto) {
        return groupMapper.toReadDto(groupService.createGroup(dto));
    }

    @IsUser
    @GetMapping("/")
    public Page<GroupReadDto> getGroups(Pageable pageable) {
        return groupService.getAllGroups(pageable)
            .map(groupMapper::toReadDto);
    }
    
    @IsAdmin
    @GetMapping("/{id}")
    public GroupReadDto getGroup(@PathVariable Integer id) {
        return groupMapper.toReadDto(null);
    }
    @IsGroupOwner
    @PatchMapping("/{id}")
    public GroupReadDto updateGroup(Integer id) {
        return null;
    }
}
