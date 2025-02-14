package com.ensselprac.domain.user.repository;

import com.ensselprac.domain.user.User;
import com.ensselprac.domain.user.request.UserSearchCondition;
import com.ensselprac.domain.user.response.UserCountByRegiDate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

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
        String useYn = searchCondition.useYn();
        return queryFactory
                .selectFrom(user)
                .where(user.useYn.eq(useYn == null ? "Y" : useYn),
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

    @Override
    public List<UserCountByRegiDate> getMemberCountByCondition(UserSearchCondition searchCondition) {
        return queryFactory
                .select(Projections.constructor(UserCountByRegiDate.class,
                        user.registerDateTime.as("regiDateTime"), user.count()))
                .from(user)
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
                .groupBy(user.registerDateTime)
                .fetch();
    }


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
        if (filterString == null || filterString.isBlank()) {
            return null;
        }
        return path.like(likePattern(filterString));
    }

    private String likePattern(String value) {
        return value + "%";
    }
}
