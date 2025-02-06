package com.ensselprac.domain.user.request;

import java.util.List;

public record UserInactivateRequest(List<String> userIds) {
}
