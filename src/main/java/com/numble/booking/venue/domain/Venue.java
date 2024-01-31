package com.numble.booking.venue.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.seat.domain.Seat;
import com.numble.booking.venue.type.VenuesType;
import com.numble.booking.venue.value.VenueCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : Venue
 * Description : 공연장
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
public class Venue extends CreatedAndModifiedBase {
    @Id
    @Column(name = "venueId", nullable = false)
    @GeneratedValue(generator = "venueSeqGenerator")
    @GenericGenerator(name = "venueSeqGenerator", strategy = "com.numble.booking.common.util.SeqGenerator")
    private Long id;

    // 공연장 이름
    @Column(nullable = false, length = 100)
    private String name;

    // 수용인원
    @Column(nullable = false)
    private Long capacity;

    // 사용가능 시간 ex. 10:00~20:00
    @Column(nullable = false)
    private String possibleTimes;

    // 공연장 유형
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VenuesType type;

    // 좌석
    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    /**
     * 생성
     */
    public static Venue create(VenueCreateDto dto) {
        Venue entity = new Venue();
        entity.name = dto.getName();
        entity.capacity = dto.getCapacity();
        entity.possibleTimes = dto.getOpenTime() + "~" + dto.getClosedTime();
        entity.type = dto.getVenuesType();
        return entity;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
