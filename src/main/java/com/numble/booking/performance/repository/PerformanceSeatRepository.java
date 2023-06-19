package com.numble.booking.performance.repository;

import com.numble.booking.performance.domain.PerformanceSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * <pre>
 * Class Name : PerformanceSeatRepository
 * Description :
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
public interface PerformanceSeatRepository extends JpaRepository<PerformanceSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ps FROM PerformanceSeat ps INNER JOIN ps.performance p INNER JOIN ps.seat s " +
            "WHERE p.id = :performanceId AND s.id IN (:seatIds)")
    List<PerformanceSeat> findByPerformanceAndBySeats(@Param("performanceId") Long performanceId, @Param("seatIds") List<Long> seatIds);

    @Query("SELECT ps FROM PerformanceSeat ps INNER JOIN ps.performance p INNER JOIN ps.user u " + "" +
            "WHERE p.id = :performanceId AND u.id = :userId")
    List<PerformanceSeat> findByPerformanceAndUser(@Param("performanceId") Long performanceId, @Param("userId") Long userId);
}
