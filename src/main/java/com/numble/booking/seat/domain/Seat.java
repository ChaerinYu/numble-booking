package com.numble.booking.seat.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.seat.type.SeatType;
import com.numble.booking.venue.domain.Venue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Seat
 * Description : 좌석
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-14	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-14
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends CreatedAndModifiedBase {
    @Id
    @Column(name = "seatId", nullable = false)
    @GeneratedValue(generator = "seatSeqGenerator")
    @GenericGenerator(name = "seatSeqGenerator", strategy = "com.numble.booking.common.util.SeqGenerator")
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venueId", nullable = false)
    private Venue venue;

    /**
     * 생성
     */
    public static Seat create(String number, SeatType type, Venue venue) {
        Seat entity = new Seat();
        entity.seatNumber = number;
        entity.seatType = type;
        entity.venue = venue;
        return entity;
    }
}
