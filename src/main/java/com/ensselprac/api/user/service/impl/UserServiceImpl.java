package com.ensselprac.api.user.service.impl;

import com.ensselprac.api.user.service.UserService;
import com.ensselprac.domain.user.User;
import com.ensselprac.domain.user.UserRepository;
import com.ensselprac.domain.user.exception.UserNotFoundException;
import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.request.UserInactivateRequest;
import com.ensselprac.domain.user.request.UserUpdateRequest;
import com.ensselprac.domain.user.response.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserSummary> findAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserSummary::from)
                .toList();
    }

    @Override
    @Transactional
    public boolean saveUser(UserCreateRequest userCreateRequest) {
        log.info("Saving user {}", userCreateRequest);
        try {
            User user = userCreateRequest.toEntity();
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error while saving user", e);
            throw new UserNotFoundException(e);
        }
    }

    @Override
    @Transactional
    public boolean updateUser(
            String currentSessionUserId,
            UserUpdateRequest updateRequest,
            LocalDateTime updateDateTime) {
        User findUser = userRepository.findById(updateRequest.id())
                .orElseThrow(UserNotFoundException::new);
        findUser.updateBy(currentSessionUserId, updateRequest.name(),
                updateRequest.password(), updateDateTime);
        return true;
    }

    @Override
    @Transactional
    public boolean invalidateUsers(String updateUserId, LocalDateTime updateDateTime,
                                   UserInactivateRequest userInvalidateRequest) {
        List<User> users = userRepository.findAllById(userInvalidateRequest.userIds());
        invalidateUsers(updateUserId, updateDateTime, users);
        return true;
    }

    private void invalidateUsers(String updateUserId,
                                        LocalDateTime updateDateTime, List<User> users) {
        users.forEach(user -> user.invalidate(updateUserId, updateDateTime));
    }
}
