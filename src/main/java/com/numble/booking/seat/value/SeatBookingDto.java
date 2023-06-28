package com.numble.booking.seat.value;

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
@Getter
@Builder
@NoArgsConstructor
public class SeatBookingDto {

    @NotNull(message = "좌석 ID가 없습니다.")
    private Long seatId;

    @NotBlank(message = "좌석 번호가 없습니다.")
    @Size(max = 256, message = "좌석 번호는 256자를 넘길 수 없습니다.")
    private String seatNumber;

    @Builder
    public SeatBookingDto(Long seatId, String seatNumber) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
    }
}
