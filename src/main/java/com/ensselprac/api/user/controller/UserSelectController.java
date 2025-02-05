package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.api.user.service.UserService;
import com.ensselprac.domain.user.response.UserSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ensselprac.api.ApiResponse.ok;

@RestController
@RequestMapping("/bi/user")
public class UserSelectController {

    private final UserService userService;

    public UserSelectController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/table")
    public ApiResponse<List<UserSummary>> getAllUsers() {
        List<UserSummary> userSummaries = userService.findAllUsers();

        return ok(userSummaries);
    }
}
