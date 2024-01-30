package com.numble.booking.venue.repository;

import com.numble.booking.venue.type.VenuesType;
import com.numble.booking.venue.value.VenueDetailVo;
import com.numble.booking.venue.value.VenueFindDto;
import com.numble.booking.venue.value.VenueListVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.numble.booking.venue.domain.QVenue.venue;

/**
 * <pre>
 * Class Name : VenueQuerydslRepository
 * Description : 공연장 querydsl
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-18	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-18
 */
@Repository
@RequiredArgsConstructor
public class VenueQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<VenueListVo> findAll(Pageable pageable, VenueFindDto dto) {
        List<VenueListVo> list = createFindVenuesQuery(dto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = createFindVenuesCountQuery(dto);

        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    private JPAQuery<VenueListVo> createFindVenuesQuery(VenueFindDto dto) {
        return queryFactory.select(
                        Projections.fields(
                                VenueListVo.class,
                                venue.id.as("venueId"),
                                venue.name,
                                venue.capacity,
                                venue.possibleTimes,
                                venue.type
                        )
                )
                .from(venue)
                .where(
                        createVenueFindBuilder(dto)
                );
    }

    /**
     * 공연장 count 조회
     */
    private JPAQuery<Long> createFindVenuesCountQuery(VenueFindDto dto) {
        return queryFactory
                .select(venue.count())
                .from(venue)
                .where(createVenueFindBuilder((dto)));
    }

    /**
     * 공연장 조회 where
     */
    private BooleanBuilder createVenueFindBuilder(VenueFindDto dto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(likeVenueName(dto.getName()));
        builder.and(goeCapacity(dto.getFromCapacity()));
        builder.and(loeCapacity(dto.getToCapacity()));
        builder.and(equalVenueType(dto.getType()));
        return builder;
    }

    /**
     * 공연장 이름 검색
     */
    private BooleanExpression likeVenueName(String name) {
        return StringUtils.hasText(name) ? venue.name.contains(name) : null;
    }

    /**
     * 공연장 수용인원 조회
     */
    private BooleanExpression goeCapacity(Long fromCapacity) {
        return Objects.nonNull(fromCapacity) ? venue.capacity.goe(fromCapacity) : null;
    }
    private BooleanExpression loeCapacity(Long toCapacity) {
        return Objects.nonNull(toCapacity) ? venue.capacity.loe(toCapacity) : null;
    }

    /**
     * 공연장 유형 검색
     */
    private BooleanExpression equalVenueType(VenuesType type) {
        return type != null ? venue.type.eq(type) : null;
    }

    /**
     * 공연장 상세 조회
     */
    public VenueDetailVo find(Long venueId) {
        return queryFactory
                .select(
                        Projections.fields(
                                VenueDetailVo.class,
                                venue.id.as("venueId"),
                                venue.name,
                                venue.capacity,
                                venue.possibleTimes,
                                venue.type
                        ))
                .from(venue)
                .where(venue.id.eq(venueId))
                .fetchOne();
    }
}
