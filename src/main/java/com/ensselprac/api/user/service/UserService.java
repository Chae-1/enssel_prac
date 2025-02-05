package com.ensselprac.api.user.service;

import com.ensselprac.domain.user.request.UserCreateRequest;
import com.ensselprac.domain.user.response.UserSummary;

import java.util.List;

public interface UserService {

    List<UserSummary> findAllUsers();

    boolean saveUser(UserCreateRequest userCreateRequest);
}
