package com.numble.booking.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numble.booking.payment.domain.Payment;

/**
 * <pre>
 * Class Name : PaymentRepository
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-20 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-20
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
