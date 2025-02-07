package com.ensselprac.api.user.service;

import com.ensselprac.domain.user.repository.UserRepository;
import com.ensselprac.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("등록되어있는 모든 유저 조회")
    void findAllUsers() {

    }
}