package com.numble.booking.venue.value;

import com.numble.booking.venue.type.VenuesType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : VenueFindDto
 * Description : 공연장 검색 DTO
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
public class VenueFindDto {

    // 공연장 이름
    private String name;

    // 수용인원
    private Long fromCapacity;
    private Long toCapacity;

    // 공연장 유형
    private VenuesType type;
}
