package com.numble.booking.price.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.seat.type.SeatType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : PricePolicy
 * Description : 금액 정책
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
public class PricePolicy extends CreatedAndModifiedBase {

    @Id
    @Column(name = "pricePolicyId", nullable = false)
    @GeneratedValue(generator = "pricePolicySeqGenerator")
    @GenericGenerator(name = "pricePolicySeqGenerator", strategy = "com.numble.booking.common.util.SeqGenerator")
    private Long id;

    // 공연
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performanceId", nullable = false, updatable = false)
    private Performance performance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;
    
    // 가격
    @Column(nullable = false)
    private Integer price;

    /**
     * 생성
     */
    public static PricePolicy create(Performance performance, SeatType type, Integer price) {
        PricePolicy entity = new PricePolicy();
        entity.performance = performance;
        entity.seatType = type;
        entity.price = price;
        return entity;
    }
}
