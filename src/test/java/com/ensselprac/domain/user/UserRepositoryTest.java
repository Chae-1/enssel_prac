package com.ensselprac.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource dataSource;
    
    @Test
    void test() {
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("ADMIN User Id를 조회하면 name이 관리자이다.")
    void findOne_Admin() {
        User admin = userRepository.findById("ADMIN")
                .orElseThrow(IllegalArgumentException::new);

        assertThat(admin)
                .extracting("name", "id")
                .containsExactly("관리자", "ADMIN");
    }
}