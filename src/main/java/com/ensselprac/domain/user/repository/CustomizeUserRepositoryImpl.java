package com.ensselprac.domain.user.repository;

import com.ensselprac.domain.user.User;
import com.ensselprac.domain.user.request.UserSearchCondition;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.ensselprac.domain.user.QUser.*;

@Repository
public class CustomizeUserRepositoryImpl implements CustomizeUserRepository {
    private final JPAQueryFactory queryFactory;

    public CustomizeUserRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> findAllByCondition(UserSearchCondition searchCondition) {
        return queryFactory
                .selectFrom(user)
                .where(user.useYn.eq(searchCondition.useYn()),
                        likeAt(user.name, searchCondition.name()),
                        likeAt(user.id, searchCondition.id()),
                        likeAt(user.registerUserId, searchCondition.registerUserId()),
                        likeAt(user.updateUserId, searchCondition.updateUserId()),
                        filterByDateRange(user.registerDateTime, searchCondition.registerDateFrom(),
                                searchCondition.registerDateTo()),
                        filterByDateRange(user.updateDateTime, searchCondition.updateDateFrom(),
                                searchCondition.updateDateTo())
                        )
                .fetch();
    }


    // from과 to가 있냐 없냐에 따라 gt, lt일 수도 있는데?

    private Predicate filterByDateRange(DateTimePath<LocalDateTime> dateTime,
                                  LocalDateTime from, LocalDateTime to) {
        if (from == null && to == null) {
            return null;
        }

        // to만 존재한다? 과거 시점부터 to 시점까지 조회
        if (from == null) {
            return dateTime.loe(to);
        }

        // from만 존재한다? 특정 과거 시점부터 조회한다.
        if (to == null) {
            return dateTime.goe(from);
        }

        return dateTime.between(from, to);
    }

    private Predicate likeAt(StringPath path, String filterString) {
        if (filterString == null) {
            return null;
        }
        return path.like(likePattern(filterString));
    }

    private Predicate registerDateTimeBetween(LocalDateTime from,
                                              LocalDateTime to) {
        // 1. from과 to 둘 중 하나라도 null일 경우
        if (from == null || to == null) {
            return null;
        }
        return user.registerDateTime.between(from, to);
    }


    private Predicate updateUserIdLike(String updateUserId) {
        if (updateUserId == null) {
            return null;
        }
        return user.name.like(likePattern(updateUserId));
    }

    private Predicate registerUserIdLike(String registerUserId) {
        if (registerUserId == null) {
            return null;
        }
        return user.name.like(likePattern(registerUserId));
    }

    private Predicate idLike(String userId) {
        if (userId == null) {
            return null;
        }
        return user.name.like(likePattern(userId));
    }

    private BooleanExpression nameLike(String name) {
        if (name == null) {
            return null;
        }
        return user.name.like(likePattern(name));
    }

    private String likePattern(String value) {
        return "%" + value + "%";
    }
}
