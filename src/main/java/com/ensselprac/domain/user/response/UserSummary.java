package com.ensselprac.domain.user.response;

import com.ensselprac.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.*;

public record UserSummary(String id, String name, String password,
                          @JsonFormat(shape = Shape.STRING, pattern = "yyyy/MM/dd") LocalDateTime registerDateTime,
                          String registerUserId, String updateUserId,
                          @JsonFormat(shape = Shape.STRING, pattern = "yyyy/MM/dd") LocalDateTime updateDateTime,
                          String useYn) {
    public static UserSummary from(User user) {
        return new UserSummary(user.getId(), user.getName(), user.getPassword(),
                user.getRegisterDateTime(), user.getRegisterUserId(),
                user.getUpdateUserId(), user.getUpdateDateTime(), user.getUseYn());
    }
}
