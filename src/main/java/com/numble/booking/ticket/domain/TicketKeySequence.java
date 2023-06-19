package com.numble.booking.ticket.domain;

import com.numble.booking.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <pre>
 * Class Name : TicketKeySequence
 * Description : 티켓 키 시퀀스
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
//@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketKeySequence {

    @Id
    @Column(name = "ticketKeyId", nullable = false)
    @GeneratedValue(generator = "ticketKeySeqGenerator")
    @GenericGenerator(name = "ticketKeySeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false, updatable = false)
    private Ticket ticket;

    @Column(nullable = false)
    private Long seq;

    /**
     * 생성
     */
    public static TicketKeySequence create(Ticket ticket) {
        TicketKeySequence entity = new TicketKeySequence();
        entity.ticket = ticket;
        entity.seq = 1L;
        return entity;
    }

    /**
     * 키 생성
     */
    public String createKey(Performance performance, Long seq) {
        return "T" + performance.getId() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%08d", seq);
    }

    /**
     * sequence + size
     */
    public void increaseSeq(int size) {
        this.seq = this.seq+size;
    }
}
