package com.numble.booking.ticket.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.order.domain.OrderItem;
import com.numble.booking.ticket.type.ReceivingMethod;
import com.numble.booking.ticket.type.TicketStatus;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Ticket
 * Description : 티켓
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends CreatedAndModifiedBase {

    @Id
    @Column(name = "ticketId", nullable = false)
    @GeneratedValue(generator = "ticketSeqGenerator")
    @GenericGenerator(name = "ticketSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 티켓 번호
    @Column(nullable = false)
    private String ticketKey;
    
    // 예매 항목
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderItem")
    private OrderItem orderItem;
    
    // 티켓 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    private User user;
    
    // 티켓 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;
    
    // 티켓 수령 방법
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReceivingMethod receivingMethod;

    /**
     * 생성
     */
    public static Ticket create(String ticketKey, OrderItem orderItem, User user, ReceivingMethod receivingMethod) {
        Ticket entity = new Ticket();
        entity.ticketKey = ticketKey;
        entity.orderItem = orderItem;
        entity.user = user;
        entity.status = TicketStatus.CONFIRMED;
        entity.receivingMethod = receivingMethod;
        return entity;
    }
}
