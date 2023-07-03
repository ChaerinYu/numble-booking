package com.numble.booking.ticket.repository;

import java.util.List;

import com.numble.booking.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class Name : TicketRepository
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
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t INNER JOIN FETCH t.user u " +
            "WHERE u.id = :userId")
    List<Ticket> findByUser(@Param("userId") Long userId);
}
