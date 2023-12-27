package com.numble.booking.book.value;

import com.numble.booking.delivery.value.DeliveryCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class Name : BookingSecondDto
 * Description : 공연/좌석 선택 후, 결제 DTO
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
public class BookingSecondDto {

    @NotNull(message = "공연을 선택해 주세요.")
    private Long performanceId;

    // 배송 정보
    private DeliveryCreateDto deliveryDto;

    private Long userId;
}
