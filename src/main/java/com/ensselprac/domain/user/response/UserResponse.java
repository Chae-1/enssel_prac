package com.ensselprac.domain.user.response;

import com.ensselprac.domain.user.User;

import java.time.LocalDateTime;

public record UserResponse(String id, String name, String password, LocalDateTime registerDateTime
        , String registerUserId, String updateUserId, LocalDateTime updateDateTime, String useYn) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getPassword(),
                user.getRegisterDateTime(), user.getRegisterUserId(),
                user.getUpdateUserId(), user.getUpdateDateTime(), user.getUseYn());
    }
}
