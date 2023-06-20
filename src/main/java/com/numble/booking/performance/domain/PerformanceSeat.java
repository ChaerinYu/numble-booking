package com.numble.booking.performance.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.type.SeatType;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * <pre>
 * Class Name : PerformanceSeat
 * Description : 공연 좌석
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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceSeat extends CreatedAndModifiedBase {

    @Id
    @Column(name = "performanceSeatId", nullable = false)
    @GeneratedValue(generator = "performanceSeatSeqGenerator")
    @GenericGenerator(name = "performanceSeatSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 공연
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performanceId", nullable = false)
    private Performance performance;

    // 공연 좌석
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat")
    private Seat seat;

    // 공연별로 좌석 번호가 다를 수도 있음 (현재는 동일)
    // default로 Seat의 number와 동일
    @Column(nullable = false)
    private String number;

    // 공연별로 좌석 type이 다를 수도 있음 (현재는 동일)
    // default로 Seat의 type과 동일
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status = SeatStatus.AVAILABLE;
    
    // 좌석 예약한 사람
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 생성
     */
    public static PerformanceSeat create(Performance performance, Seat seat) {
        PerformanceSeat entity = new PerformanceSeat();
        entity.performance = performance;
        entity.seat = seat;
        entity.number = seat.getNumber();
        entity.seatType = seat.getSeatType();
        entity.status = SeatStatus.AVAILABLE;
        return entity;
    }

    /**
     * 상태 업데이트
     */
    public void modifyStatus(SeatStatus status, User user) {
        this.status = status;
        List<SeatStatus> userNull = List.of(SeatStatus.AVAILABLE, SeatStatus.BLOCKED, SeatStatus.UNAVAILABLE);
        this.user = !userNull.contains(status) ? user : null;
    }
}
