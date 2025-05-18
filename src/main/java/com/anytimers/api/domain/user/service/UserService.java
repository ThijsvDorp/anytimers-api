package com.anytimers.api.domain.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anytimers.api.domain.auth.exception.UserNotFoundException;
import com.anytimers.api.domain.user.controller.dto.UserWriteDto;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.data.UserRepository;
import com.anytimers.api.domain.user.exception.UserAlreadyExistsException;
import com.anytimers.api.domain.user.mapper.UserMapper;
import com.anytimers.api.util.PrincipalUtil;
import com.anytimers.api.util.service.EntityService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService extends EntityService<User, UserRepository> {

    private final UserMapper userMapper;

    private final PrincipalUtil principalUtil;

    public UserService(UserRepository repository, UserMapper userMapper, PrincipalUtil principalUtil) {
        super("user", repository);
        this.userMapper = userMapper;
        this.principalUtil = principalUtil;
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

    public User getCurrentUser() {
        return principalUtil.getCurrentUser();
    }
    
    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    };

    public User getUserById(Integer userId) {
        return repository.getReferenceById(userId);
    }

    /**
     * Updates a user by its fields
     * @param id The id of the user that is being updated
     * @param dto The updated values
     * @return {@link UserReadDto} object containing the updated values
     */
    public User update(Integer id, UserWriteDto dto) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (repository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("email", dto.getEmail());
        }

        if (repository.existsByUserName(dto.getUserName())) {
            throw new UserAlreadyExistsException("username", dto.getUserName());
        }

        userMapper.updateUserFromDto(dto, user);

        return repository.save(user);
    }

    /**
     * Deletes a user by their user id
     * @param userId the user id of the to be deleted user
     */
    public void delete(Integer userId) {
        repository.deleteById(userId);
    }

    /**
     * Deletes a user by either their email or username
     * @param identifier email or username value
     */
    public void delete(String identifier) {
        repository.deleteByUserNameOrEmail(identifier, identifier);
    }
    
    /**
     * 
     * @param identifier The email or username
     * @return {@link User} object
     */
    public User getUserByNameOrEmail(String identifier) {
        return repository.findByUserNameOrEmail(identifier, identifier)
            .orElseThrow(() -> new UserNotFoundException("No user could be found"));
    };

    public Set<User> map(Set<Integer> userIds) {
        return new HashSet<>(repository.findAllById(userIds));
    }
}
