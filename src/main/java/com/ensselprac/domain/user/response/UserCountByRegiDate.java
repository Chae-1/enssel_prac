package com.ensselprac.domain.user.response;

import java.time.LocalDateTime;

public record UserCountByRegiDate(LocalDateTime regiDateTime,
                                  int count) {
}
