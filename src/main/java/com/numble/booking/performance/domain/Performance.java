package com.numble.booking.performance.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.price.domain.PricePolicy;
import com.numble.booking.venue.domain.Venue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : Performance
 * Description : 공연
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
public class Performance extends CreatedAndModifiedBase {
    @Id
    @Column(name = "performanceId", nullable = false)
    @GeneratedValue(generator = "performanceSeqGenerator")
    @GenericGenerator(name = "performanceSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 공연장
    @ManyToOne
    @JoinColumn(name = "venueId", nullable = false, updatable = false)
    private Venue venue;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    
    // 수용인원
    @Column(nullable = false)
    private Long capacity;

    // 공연 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // 공연 시작 시간
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    
    // 공연 종료 시간
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    
    // 공연 가격
    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PricePolicy> pricePolicies = new ArrayList<>();
    
    // 적용 가능한 쿠폰
    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    private List<PerformanceCoupon> performanceCoupons = new ArrayList<>();

    /**
     * 생성
     */
    public static Performance create(Venue venue, PerformanceCreateDto dto) {
        Performance entity = new Performance();
        entity.venue = venue;
        entity.name = dto.getName();
        entity.description = dto.getDescription();
        entity.genre = dto.getGenre();
        entity.capacity = dto.getCapacity() != null ? dto.getCapacity() : venue.getCapacity();
        entity.date = dto.getDate();
        entity.startTime = dto.getStartTime();
        entity.endTime = dto.getEndTime();
        return entity;
    }
}
