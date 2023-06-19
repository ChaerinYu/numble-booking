package com.numble.booking.payment.repository;

import com.numble.booking.payment.domain.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : PaymentInfoRepository
 * Description :
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
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
}
