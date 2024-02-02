package com.numble.booking.venue.value;

import com.numble.booking.venue.type.VenuesType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("공연장 목록")
@Getter
@NoArgsConstructor
public class VenueListVo {

    @ApiModelProperty("공연장 ID")
    private Long venueId;

    @ApiModelProperty("공연장 이름")
    private String name;

    @ApiModelProperty("공연장 수용 인원")
    private Long capacity;

    @ApiModelProperty("사용 가능 시간")
    private String possibleTimes;

    @ApiModelProperty("공연장 유형")
    private VenuesType type;
}
