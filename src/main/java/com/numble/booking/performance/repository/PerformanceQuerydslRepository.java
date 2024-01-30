package com.numble.booking.performance.repository;

import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceDetailVo;
import com.numble.booking.performance.value.PerformanceFindDto;
import com.numble.booking.performance.value.PerformanceListVo;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.numble.booking.performance.domain.QPerformance.performance;
import static com.numble.booking.venue.domain.QVenue.venue;

/**
 * <pre>
 * Class Name : PerformanceQuerydslRepository
 * Description : 공연 querydsl
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Repository
@RequiredArgsConstructor
public class PerformanceQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<PerformanceListVo> findAll(Long venueId) {
        PerformanceFindDto dto = new PerformanceFindDto();
        dto.setVenueId(venueId);
        return createFindPerformancesQuery(dto)
                .fetch();
    }

    public Page<PerformanceListVo> findAll(Pageable pageable, PerformanceFindDto dto) {
        List<PerformanceListVo> list = createFindPerformancesQuery(dto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = createFindPerformancesCountQuery(dto);

        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    public PerformanceDetailVo find(Long performanceId) {
        return queryFactory.select(
                        Projections.fields(
                                PerformanceDetailVo.class,
                                performance.id.as("performanceId"),
                                venue.id.as("venueId"),
                                venue.name.as("venueName"),
                                performance.name,
                                performance.description,
                                performance.genre,
                                performance.performanceDate,
                                performance.startTime,
                                performance.endTime
                        )
                )
                .from(performance)
                .innerJoin(performance.venue, venue)
                .where(
                        performance.id.eq(performanceId)
                )
                .fetchOne();
    }

    private JPAQuery<PerformanceListVo> createFindPerformancesQuery(PerformanceFindDto dto) {
        return queryFactory.select(
                        Projections.fields(
                                PerformanceListVo.class,
                                performance.id.as("performanceId"),
                                venue.id.as("venueId"),
                                venue.name.as("venueName"),
                                performance.name,
                                performance.genre,
                                performance.performanceDate,
                                performance.startTime,
                                performance.endTime
                        )
                )
                .from(performance)
                .innerJoin(performance.venue, venue)
                .where(
                        createPerformanceFindBuilder(dto)
                );
    }

    /**
     * 공연 조회 where
     */
    private BooleanBuilder createPerformanceFindBuilder(PerformanceFindDto dto) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(equalVenue(dto.getVenueId()));
        builder.and(likePerformanceName(dto.getName()));
        builder.and(equalGenre(dto.getGenre()));
        builder.and(goeDate(dto.getFromDate()));
        builder.and(loeDate(dto.getToDate()));

        return builder;
    }

    /**
     * 공연 조회 count
     */
    private JPAQuery<Long> createFindPerformancesCountQuery(PerformanceFindDto dto) {
        return queryFactory
                .select(performance.count())
                .from(performance)
                .innerJoin(performance.venue, venue)
                .where(createPerformanceFindBuilder(dto));
    }

    private BooleanExpression equalVenue(Long venueId) {
        return venueId != null ? venue.id.eq(venueId) : null;
    }

    private BooleanExpression likePerformanceName(String name) {
        return StringUtils.hasText(name) ? performance.name.contains(name) : null;
    }

    private BooleanExpression equalGenre(Genre genre) {
        return genre != null ? performance.genre.eq(genre) : null;
    }

    private BooleanExpression goeDate(LocalDate fromDate) {
        return Objects.nonNull(fromDate) ? performance.performanceDate.goe(fromDate) : null;
    }

    private BooleanExpression loeDate(LocalDate toDate) {
        return Objects.nonNull(toDate) ? performance.performanceDate.loe(toDate) : null;
    }
}
