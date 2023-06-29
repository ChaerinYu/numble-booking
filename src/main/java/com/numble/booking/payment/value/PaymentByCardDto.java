package com.numble.booking.payment.value;

import com.numble.booking.payment.type.Bank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * <pre>
 * Class Name : PaymentByCardDto
 * Description : 신용카드, 직불카드
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
public class PaymentByCardDto {

    private Bank bank;

    @Size(max = 20, message = "잘못된 카드 번호 형식입니다.")
    private String cardNumber;

    @Size(max = 5, message = "잘못된 유효기간 형식입니다.")
    private String cardExpiration;

    @Max(999)
    private Integer cardCVV;
}
