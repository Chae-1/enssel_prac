package com.ensselprac.domain.user.repository;

import com.ensselprac.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, CustomizeUserRepository {
}
