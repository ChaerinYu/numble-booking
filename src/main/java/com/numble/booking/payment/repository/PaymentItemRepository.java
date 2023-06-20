package com.numble.booking.payment.repository;

import com.numble.booking.payment.domain.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : PaymentItemRepository
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-21
 */
@Repository
public interface PaymentItemRepository extends JpaRepository<PaymentItem, Long> {
}
