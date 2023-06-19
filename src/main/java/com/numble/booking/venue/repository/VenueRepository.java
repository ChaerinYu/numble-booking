package com.numble.booking.venue.repository;

import com.numble.booking.venue.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : VenueRepository
 * Description : 공연장 repository
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
public interface VenueRepository extends JpaRepository<Venue, Long> {
}
