package com.ensselprac.api.user.service;

import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.request.UserInactivateRequest;
import com.ensselprac.domain.user.request.UserUpdateRequest;
import com.ensselprac.domain.user.response.UserSummary;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    List<UserSummary> findAllUsers();

    boolean saveUser(UserCreateRequest userCreateRequest);

    boolean updateUser(String updateUserId,
                       UserUpdateRequest updateRequest,
                       LocalDateTime updateDateTime);

    boolean invalidateUsers(String updateUserId, LocalDateTime updateDateTime,
                            UserInactivateRequest userInactivateRequest);
}
