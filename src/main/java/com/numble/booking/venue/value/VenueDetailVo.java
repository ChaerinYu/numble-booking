package com.numble.booking.venue.value;

import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.seat.value.VenueSeatListVo;
import com.numble.booking.venue.type.VenuesType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : VenueDetailVo
 * Description : 공연장 상세 vo
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
@Getter
@Setter
@NoArgsConstructor
public class VenueDetailVo {

    // 공연장 ID
    private Long venueId;

    // 공연장 이름
    private String name;

    // 수용 인원
    private Long capacity;

    // 사용 가능 시간
    private String possibleTimes;

    // 공연장 유형
    private VenuesType type;

    // 좌석
    private List<VenueSeatListVo> seats = new ArrayList<>();

    // 예약된 공연장 목록
    private List<PerformanceListVo> performances = new ArrayList<>();
}
