package com.numble.booking.seat.value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * Class Name : SeatBookingDto
 * Description :
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
@ApiModel("좌석 예약 DTO")
@Getter
@NoArgsConstructor
public class SeatBookingDto {

    @ApiModelProperty("좌석 ID")
    @NotNull(message = "좌석 ID가 없습니다.")
    private Long performanceSeatId;

    @ApiModelProperty("좌석 번호")
    @NotBlank(message = "좌석 번호가 없습니다.")
    @Size(max = 255, message = "좌석 번호는 255자를 넘길 수 없습니다.")
    private String seatNumber;

    @Builder
    public SeatBookingDto(Long performanceSeatId, String seatNumber) {
        this.performanceSeatId = performanceSeatId;
        this.seatNumber = seatNumber;
    }
}
