package com.ensselprac.domain.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "pk_test_user", columnNames = "USER_ID")
        })
public class User {
    @Id
    @Column(name = "USER_ID", nullable = false, length = 20)
    private String id;

    @Column(name = "USER_NM", nullable = false, length = 20)
    private String name;

    @Column(name = "PW", nullable = false, length = 30)
    private String password;

    @Column(name = "REGI_DT", columnDefinition = "datetime default getdate()")
    private LocalDateTime registerDateTime;

    @Column(name = "REGI_USER", length = 10, nullable = false)
    private String registerUserId;

    @Column(name = "UPDA_DT", columnDefinition = "datetime")
    private LocalDateTime updateDateTime;

    @Column(name = "UPDA_USER", length = 10)
    private String updateUserId;

    @Column(name = "USE_YN", columnDefinition = "varchar(1) not null default 'Y'")
    private String useYn;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public String getRegisterUserId() {
        return registerUserId;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public String getUseYn() {
        return useYn;
    }
}
