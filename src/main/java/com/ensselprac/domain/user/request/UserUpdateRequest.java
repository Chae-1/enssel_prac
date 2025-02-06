package com.ensselprac.domain.user.request;

public record UserUpdateRequest(String id,
                                String name,
                                String password) {
}
