package com.numble.booking.seat.value;

import com.numble.booking.seat.type.SeatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * Class Name : VenueSeatDto
 * Description : 공연장 좌석 dto
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
public class VenueSeatDto {

    @NotBlank(message = "좌석 명칭을 반드시 입력 해 주세요.")
    @Size(max = 256, message = "좌석 명칭은 256자를 넘길 수 없습니다.")
    private String seatNumber;

    @NotNull(message = "좌석 유형을 입력 해 주세요.")
    private SeatType seatType;
}
