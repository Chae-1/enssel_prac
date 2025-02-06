package com.ensselprac.domain.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "pk_test_user", columnNames = "USER_ID")
        })
public class User {

    private static final String USE_Y = "Y";
    private static final String USE_N = "N";

    private static final String ADMIN_USER_NAME = "ADMIN";

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

    // todo: 사용여부에 따라 다른 동작이 있다면 Enum으로 바꾸자.
    @Column(name = "USE_YN", columnDefinition = "varchar(1) not null default 'Y'")
    private String useYn;

    @PrePersist
    public void prePersist() {
        if (this.useYn == null) {
            this.useYn = USE_Y;
        }

        if (this.registerDateTime == null) {
            this.registerDateTime = LocalDateTime.now();
        }
    }

    protected User() {
    }

    private User(String id, String name, String password, LocalDateTime registerDateTime, String registerUserId,
                 LocalDateTime updateDateTime, String updateUserId, String useYn) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.registerDateTime = registerDateTime;
        this.registerUserId = registerUserId;
        this.updateDateTime = updateDateTime;
        this.updateUserId = updateUserId;
        this.useYn = useYn;
    }

    public static User ofNew(String id, String name,
                             String password, String registerUserId) {
        return new User(id, name, password, null, registerUserId,
                null, null, USE_Y);
    }

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

    public void updateBy(String updateUser, String name, String password,
                         LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        this.name = name;
        this.password = password;
        this.updateUserId = updateUser;
    }

    public void invalidate(String updateUser,
                           LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        this.updateUserId = updateUser;
        this.useYn = USE_N;
    }
}
