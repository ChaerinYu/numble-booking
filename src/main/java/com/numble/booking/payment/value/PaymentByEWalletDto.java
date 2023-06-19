package com.numble.booking.payment.value;

import com.numble.booking.common.type.YN;
import com.numble.booking.payment.type.EWallet;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : PaymentByEWalletDto
 * Description : 전자지갑
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
public class PaymentByEWalletDto {

    // 전자 지갑 결제 유무 (전자지갑일 경우 YN.Y)
    private YN eWalletYn;

    // 페이
    private EWallet eWallet;
}
