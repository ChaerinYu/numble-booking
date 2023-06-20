package com.numble.booking.performance.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : PerformanceCouponDto
 * Description : 사용하는 공연 쿠폰
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
@Getter
@Setter
@NoArgsConstructor
public class PerformanceCouponDto {

    // 사용하는 쿠폰 id
    private Long performanceCouponId;

    // 쿠폰 적용한 공연 좌석 id
    private Long performanceSeatId;
}
