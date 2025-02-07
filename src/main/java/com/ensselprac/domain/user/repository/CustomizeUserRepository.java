package com.ensselprac.domain.user.repository;

import com.ensselprac.domain.user.User;
import com.ensselprac.domain.user.request.UserSearchCondition;

import java.util.List;

public interface CustomizeUserRepository {
    List<User> findAllByCondition(UserSearchCondition searchCondition);
}
