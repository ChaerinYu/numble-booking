package com.numble.booking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numble.booking.user.domian.Business;

/**
 * <pre>
 * Class Name : BusinessRepository
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-21
 */
@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
}
