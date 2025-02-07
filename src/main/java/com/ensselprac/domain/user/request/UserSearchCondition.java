package com.ensselprac.domain.user.request;

import java.time.LocalDateTime;

public record UserSearchCondition(String name,
                                  String id,
                                  String registerUserId,
                                  String updateUserId,
                                  String useYn,
                                  LocalDateTime registerDateFrom,
                                  LocalDateTime registerDateTo,
                                  LocalDateTime updateDateFrom,
                                  LocalDateTime updateDateTo) {
}
