package com.ensselprac.api.user.service.impl;

import com.ensselprac.api.user.service.UserService;
import com.ensselprac.domain.user.User;
import com.ensselprac.domain.user.UserRepository;
import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.response.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return false;
        }
    }
}
