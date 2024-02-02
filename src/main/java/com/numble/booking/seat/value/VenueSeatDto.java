package com.numble.booking.seat.value;

import com.numble.booking.seat.type.SeatType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("공연장 좌석 DTO")
@Getter
@Setter
@NoArgsConstructor
public class VenueSeatDto {
    
    @ApiModelProperty("좌석 번호")
    @NotBlank(message = "좌석 번호를 반드시 입력 해 주세요.")
    @Size(max = 255, message = "좌석 번호는 255자를 넘길 수 없습니다.")
    private String seatNumber;

    @ApiModelProperty(value = "좌석 유형", example = "REGULAR")
    @NotNull(message = "좌석 유형을 입력 해 주세요.")
    private SeatType seatType;
}
