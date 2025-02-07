package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.domain.user.request.UserSearchCondition;
import com.ensselprac.domain.user.service.UserService;
import com.ensselprac.domain.user.response.UserSummary;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ensselprac.api.ApiResponse.ok;

@RestController
@RequestMapping("/bi/user")
public class UserSelectController {

    private static final Logger log = LoggerFactory.getLogger(UserSelectController.class);
    private final UserService userService;

    public UserSelectController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/table")
    public ApiResponse<List<UserSummary>> getAllUsers(@ModelAttribute
                                                          UserSearchCondition condition, HttpServletRequest request) {
        log.info("userSearchCondition = {}", condition);
        request.getParameterNames()
                .asIterator().forEachRemaining(name ->
                        log.info("name = {}, value = {}", name, request.getParameter(name)));
        List<UserSummary> userSummaries = userService.findUsersOnCondition(condition);
        return ok(userSummaries);
    }
}
