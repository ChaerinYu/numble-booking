package com.numble.booking.delivery.repository;

import com.numble.booking.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : DeliveryRepository
 * Description : 배달 repository
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-19	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-19
 */
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
