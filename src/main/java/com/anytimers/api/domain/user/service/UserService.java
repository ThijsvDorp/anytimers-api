package com.anytimers.api.domain.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anytimers.api.domain.auth.exception.UserNotFoundException;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.data.UserRepository;
import com.anytimers.api.domain.user.exception.UserAlreadyExistsException;
import com.anytimers.api.domain.user.mapper.UserMapper;
import com.anytimers.api.util.service.EntityService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService extends EntityService<User, UserRepository> {

    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        super("user", repository);
        this.userMapper = userMapper;
    };
    
    public User createUser(UserWriteDto dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("email", dto.getEmail());
        }
        if (repository.existsByUserName(dto.getUserName())) {
            throw new UserAlreadyExistsException("username", dto.getUserName());
        }

        User user = userMapper.toEntity(dto);
        return repository.save(user);
    };

    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    };

    /**
     * 
     * @param identifier The email or username
     * @return {@link User} object
     */
    public User getUserByNameOrEmail(String identifier) {
        return repository.findByUserNameOrEmail(identifier, identifier)
            .orElseThrow(() -> new UserNotFoundException("No user could be found"));
    };
}
