package com.anytimers.api.domain.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anytimers.api.util.authorities.annotations.IsAdmin;
import com.anytimers.api.util.authorities.annotations.IsUser;
import com.anytimers.api.domain.user.controller.dto.UserReadDto;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.domain.user.mapper.UserMapper;
import com.anytimers.api.domain.user.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
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

    @IsAdmin
    @PostMapping
    public UserReadDto createUser(@RequestBody UserWriteDto dto) {
        return userMapper.toReadDto(userService.createUser(dto));
    }
    
    @IsAdmin
    @GetMapping
    public Page<UserReadDto> getUsers(Pageable pageable) {
        return userService.getAllUsers(pageable)
            .map(userMapper::toReadDto);
    }

    @IsUser
    @GetMapping("/me")
    public UserReadDto getCurrentUser() {
        return userMapper.toReadDto(userService.getCurrentUser());
    }
    
    @IsAdmin
    @PatchMapping("/{id}")
    public UserReadDto updateUser(@PathVariable Integer id, @RequestBody UserWriteDto dto) {
        return userMapper.toReadDto(userService.update(id, dto));
    }

    @IsAdmin
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
