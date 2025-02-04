package com.ensselprac.api.user.service;

import com.ensselprac.domain.user.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAllUsers();
}
