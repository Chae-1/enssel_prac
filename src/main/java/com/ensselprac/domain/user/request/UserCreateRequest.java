package com.ensselprac.domain.user.request;

import com.ensselprac.domain.user.User;

public record UserCreateRequest(String name, String id,
                                String password, String registerUserId) {
    public User toEntity() {
        return User.ofNew(id, name, password, registerUserId);
    }
}
