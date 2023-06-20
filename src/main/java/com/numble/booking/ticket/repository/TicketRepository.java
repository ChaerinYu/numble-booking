package com.numble.booking.ticket.repository;

import com.numble.booking.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
