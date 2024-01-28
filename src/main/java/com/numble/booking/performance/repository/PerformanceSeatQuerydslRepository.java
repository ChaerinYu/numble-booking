package com.numble.booking.performance.repository;

import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatListVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.numble.booking.performance.domain.QPerformance.performance;
import static com.numble.booking.performance.domain.QPerformanceSeat.performanceSeat;

/**
 * <pre>
 * Class Name : PerformanceSeatQuerydslRepository
 * Description : 공연 좌석 querydsl
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
public class PerformanceSeatQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<SeatListVo> findAvailableSeats(Long performanceId) {
        return queryFactory
                .select(
                        Projections.fields(
                                SeatListVo.class,
                                performanceSeat.id.as("seatId"),
                                performanceSeat.seatNumber,
                                performanceSeat.status
                        )
                )
                .from(performanceSeat)
                .innerJoin(performanceSeat.performance, performance)
                .where(
                        performance.id.eq(performanceId),
                        performanceSeat.status.eq(SeatStatus.AVAILABLE)
                )
                .fetch();
    }
}
