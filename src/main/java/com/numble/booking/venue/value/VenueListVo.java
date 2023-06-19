package com.numble.booking.venue.value;

import com.numble.booking.venue.type.VenuesType;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : VenueListVo
 * Description : 공연장 목록 vo
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
@NoArgsConstructor
public class VenueListVo {

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
}
