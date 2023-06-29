package com.numble.booking.book.value;

import com.numble.booking.payment.type.PaymentMethod;
import com.numble.booking.payment.value.DeliveryCreateDto;
import com.numble.booking.payment.value.PaymentByCardDto;
import com.numble.booking.payment.value.PaymentByEWalletDto;
import com.numble.booking.performance.value.PerformanceCouponDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
@Setter
@Builder
@NoArgsConstructor
public class BookingSecondDto {

    @NotNull(message = "공연을 선택해 주세요.")
    private Long performanceId;

    @NotNull(message = "결제 방식을 선택해 주세요.")
    private PaymentMethod paymentMethod;

    // 신용카드 / 직불카드
    private PaymentByCardDto cardDto;

    // 전자 지갑
    private PaymentByEWalletDto eWalletDto;

    // 배송 정보
    private DeliveryCreateDto deliveryDto;
    
    // 사용하는 공연 쿠폰
    private List<PerformanceCouponDto> coupons = new ArrayList<>();

    private Long userId;

    @Builder
    public BookingSecondDto(Long performanceId, PaymentMethod method, PaymentByCardDto cardDto,
                            PaymentByEWalletDto eWalletDto, DeliveryCreateDto deliveryDto,
                            List<PerformanceCouponDto> coupons, Long userId) {
        this.performanceId = performanceId;
        this.paymentMethod = method;
        this.cardDto = cardDto;
        this.eWalletDto = eWalletDto;
        this.deliveryDto = deliveryDto;
        this.coupons = coupons;
        this.userId = userId;
    }
}
