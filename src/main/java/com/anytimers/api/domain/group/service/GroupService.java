package com.anytimers.api.domain.group.service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anytimers.api.domain.group.controller.dto.GroupWriteDto;
import com.anytimers.api.domain.group.data.Group;
import com.anytimers.api.domain.group.data.GroupRepository;
import com.anytimers.api.domain.group.mapper.GroupMapper;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.service.UserService;
import com.anytimers.api.util.service.EntityService;


@Service
@Transactional
public class GroupService extends EntityService<Group, GroupRepository>{
    
    private final GroupMapper groupMapper;

    private final UserService userService;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, UserService userService) {
        super("group", groupRepository);
        this.groupMapper = groupMapper;
        this.userService = userService;
    }

    /**
     * Creates a group
     * @param dto
     * @return Created user object
     */
    public Group createGroup(GroupWriteDto dto) {
        Group group = groupMapper.toEntity(dto);
        Integer userId = userService.getCurrentUser().getId();
        group.setOwnerId(userId);

        Set<User> users = userService.map(dto.getUserIds());
        group.setUsers(users);
        
        for (User user : users) {
            user.getGroups().add(group);
        }

        return repository.save(group);
    }

    public Page<Group> getAllGroups(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Used for checking whether a user is the owner of a group
     * @param groupId The id of the group
     * @param userId The id of the user
     * @return true if user is owner, false if user is not owner of group
     */
    public boolean isOwner(Integer groupId, Integer userId) {
        Group group = repository.findById(groupId).orElseThrow();
        return userId != null && group.getOwnerId() == userId;
    }
}
