package com.numble.booking.order.repository;

import com.numble.booking.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : OrderRepository
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-12-27	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-12-27
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
