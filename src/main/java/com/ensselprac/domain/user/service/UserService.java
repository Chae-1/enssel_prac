package com.ensselprac.domain.user.service;

import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.request.UserInactivateRequest;
import com.ensselprac.domain.user.request.UserSearchCondition;
import com.ensselprac.domain.user.request.UserUpdateRequest;
import com.ensselprac.domain.user.response.UserSummary;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    boolean saveUser(UserCreateRequest userCreateRequest);

    boolean updateUser(String updateUserId,
                       UserUpdateRequest updateRequest,
                       LocalDateTime updateDateTime);

    boolean invalidateUsers(String updateUserId, LocalDateTime updateDateTime,
                            UserInactivateRequest userInactivateRequest);

    List<UserSummary> findUsersOnCondition(UserSearchCondition condition);
}
