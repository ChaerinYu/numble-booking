package com.numble.booking.ticket.domain;

import com.numble.booking.order.domain.Order;
import com.numble.booking.order.domain.OrderItem;
import com.numble.booking.performance.domain.PerformanceSeat;
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
@DiscriminatorValue(value = "TICKET") // 구분 컬럼 값 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends OrderItem {

    @Id
    @Column(name = "ticketId", nullable = false)
    @GeneratedValue(generator = "ticketSeqGenerator")
    @GenericGenerator(name = "ticketSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 티켓 번호
    private String ticketKey;

    @ManyToOne
    @JoinColumn(name = "performanceSeatId")
    private PerformanceSeat performanceSeat;
    
    // 티켓 상태
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    /**
     * 생성
     */
    public static Ticket create(Order order, int orderPrice, int count, String ticketKey, PerformanceSeat seat, User user) {
        Ticket entity = new Ticket();
        entity.order = order;
        entity.orderPrice = orderPrice;
        entity.count = count;
        entity.ticketKey = ticketKey;
        entity.performanceSeat = seat;
        entity.user = user;
        entity.ticketStatus = TicketStatus.CONFIRMED;
        return entity;
    }
}
