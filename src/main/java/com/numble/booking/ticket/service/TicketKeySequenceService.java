package com.numble.booking.ticket.service;

//import com.numble.booking.ticket.domain.Ticket;
//import com.numble.booking.ticket.domain.TicketKeySequence;
//import com.numble.booking.ticket.exception.NotFoundTicketKeyException;
//import com.numble.booking.ticket.repository.TicketKeySequenceRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class Name : TicketKeySequenceService
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-20	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-20
 */
//@Service
//@RequiredArgsConstructor
//public class TicketKeySequenceService {
//
//    private final TicketKeySequenceRepository ticketKeySequenceRepository;
//
//    /**
//     * 키 sequence 정보 조회
//     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
//    public Long getKeySequence(Ticket ticket) {
//        TicketKeySequence ticketKeySequence = ticketKeySequenceRepository.findByTicket(ticket)
//                .orElseThrow(NotFoundTicketKeyException::new);
//
//        ticketKeySequence.increaseSeq(1);
//        return ticketKeySequence.getSeq();
//    }
//}
