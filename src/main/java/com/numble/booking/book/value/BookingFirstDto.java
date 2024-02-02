package com.numble.booking.book.value;

import com.numble.booking.seat.value.SeatBookingDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : BookingFirstDto
 * Description : 공연/좌석 선택 dto
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
@ApiModel("공연/좌석 선택 DTO")
@Getter
@Setter
@NoArgsConstructor
public class BookingFirstDto {

    @ApiModelProperty(value = "공연 id", required = true)
    @NotNull(message = "공연을 선택해 주세요.")
    private Long performanceId;

    @ApiModelProperty(value = "선택한 좌석", required = true)
    @NotNull(message = "좌석을 선택해 주세요.")
    private List<SeatBookingDto> seats = new ArrayList<>();

    @ApiModelProperty(value = "로그인한 사용자 id", required = true)
    @NotNull(message = "사용자 정보가 없습니다.")
    private Long userId;
}
