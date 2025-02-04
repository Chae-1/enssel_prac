create table TEST_USER (
                           USER_NM varchar(20) not null,
                           USER_ID varchar(20),
                           PW varchar(30) not null,
                           REGI_DT datetime default getdate(),
                           REGI_USER varchar(10) not null,
                           UPDA_DT datetime,
                           UPDA_USER varchar(10),
                           USE_YN varchar(1) not null default 'Y',
                           constraint PK_TEST_USER primary key (USER_ID)
);

insert into TEST_USER(USER_NM, USER_ID, PW, REGI_USER, UPDA_DT, UPDA_USER)
values('관리자', 'ADMIN', '1234', 'ADMIN', null, null);