package com.numble.booking.price.value;

import com.numble.booking.seat.type.SeatType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class Name : PricePolicyDto
 * Description : 공연 좌석별 가격 DTO
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Getter
@Builder
@NoArgsConstructor
public class PricePolicyDto {

    @NotNull(message = "좌석 유형을 입력 해 주세요.")
    private SeatType type;

    @NotNull(message = "좌석 금액을 입력 해 주세요.")
    private Integer price;

    @Builder
    public PricePolicyDto(SeatType type, Integer price) {
        this.type = type;
        this.price = price;
    }
}
