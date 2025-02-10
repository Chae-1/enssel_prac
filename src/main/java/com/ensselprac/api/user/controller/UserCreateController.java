package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.domain.user.service.UserService;
import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.request.UserInactivateRequest;
import com.ensselprac.domain.user.request.UserUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bi/user")
public class UserCreateController {
    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    private final UserService userService;

    public UserCreateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/regiUser")
    public ApiResponse<Boolean> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        log.info("사용자 등록 요청 정보 : {}", userCreateRequest);
        boolean saveResult = userService.saveUser(userCreateRequest);
        return ApiResponse.ok(saveResult);
    }

    @PostMapping("/update")
    public ApiResponse<Boolean> updateUserAndGet(@RequestBody UserUpdateRequest userUpdateRequest) {
        // 파라미터를 전달하는 부분,
        LocalDateTime updateDateTime = LocalDateTime.now();
        String admin = "ADMIN";
        return ApiResponse.ok(userService.updateUser(admin, userUpdateRequest, updateDateTime));
    }

    @PostMapping("/inactivate")
    public ApiResponse<Boolean> invalidateUser(@RequestBody UserInactivateRequest userInactivateRequest) {
        LocalDateTime updateDateTime = LocalDateTime.now();
        String admin = "ADMIN";
        return ApiResponse.ok(userService.invalidateUsers(admin, updateDateTime, userInactivateRequest));
    }
}
