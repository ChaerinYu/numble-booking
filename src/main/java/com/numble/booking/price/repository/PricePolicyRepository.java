package com.numble.booking.price.repository;

import com.numble.booking.price.domain.PricePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * Class Name : PricePolicyRepository
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-17	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-17
 */
@Repository
public interface PricePolicyRepository extends JpaRepository<PricePolicy, Long> {

    @Query("SELECT pp FROM PricePolicy pp INNER JOIN FETCH pp.performance p WHERE p.id = :performanceId")
    List<PricePolicy> findByPerformance(Long performanceId);

    @Query("SELECT pp FROM PricePolicy pp INNER JOIN FETCH pp.performance p WHERE p.id IN (:performanceIds)")
    List<PricePolicy> findByPerformanceIn(List<Long> performanceIds);

}
