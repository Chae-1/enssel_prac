package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.api.user.service.UserService;
import com.ensselprac.domain.user.request.UserCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bi/user")
public class UserCreateController {
    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    private final UserService userService;

    public UserCreateController(UserService userService) {
        this.userService = userService;
    }

    // todo: hibernate validate
    // 검증 로직을 추가.
    @PostMapping("/regiUser")
    public ApiResponse<Boolean> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return ApiResponse.ok(userService.saveUser(userCreateRequest));
    }
}
