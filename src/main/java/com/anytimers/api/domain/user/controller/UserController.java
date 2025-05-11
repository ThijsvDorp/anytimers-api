package com.anytimers.api.domain.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anytimers.api.domain.user.controller.dto.UserReadDto;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.domain.user.mapper.UserMapper;
import com.anytimers.api.domain.user.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(
    value = "/v1/users",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserReadDto createUser(@RequestBody UserWriteDto dto) {
        return userMapper.toReadDto(userService.createUser(dto));
    }
    
    @GetMapping
    public Page<UserReadDto> getUsers(Pageable pageable) {
        return userService.getAllUsers(pageable)
            .map(userMapper::toReadDto);
    }
    
    @PatchMapping("/{id}")
    public UserReadDto updateUser(@PathVariable Integer id, @RequestBody UserWriteDto dto) {
        return userMapper.toReadDto(userService.update(id, dto));
    }
}
