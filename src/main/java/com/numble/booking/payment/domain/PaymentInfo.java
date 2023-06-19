package com.numble.booking.payment.domain;

import com.numble.booking.common.type.YN;
import com.numble.booking.payment.type.Bank;
import com.numble.booking.payment.type.EWallet;
import com.numble.booking.payment.type.PaymentMethod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : PaymentInfo
 * Description : 결제 정보
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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {

    @Id
    @Column(name = "paymentInfoId", nullable = false)
    @GeneratedValue(generator = "paymentInfoSeqGenerator")
    @GenericGenerator(name = "paymentInfoSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 결제 방식
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    // 사용 포인트
    private Integer usedPoint;

    // 배송비
    private Integer deliveryPrice;
    
    // CREDIT_CARD, DEBIT_CARD
    // 은행
    private Bank bank;

    // 카드 번호 1234 1234 1234 1234
    private String cardNumber;

    // ex) 12/24
    private String cardExpiration;

    // ex) 123
    private Integer cardCVV;

    // 전자 지갑
    @Enumerated(EnumType.STRING)
    private YN eWalletYn = YN.N;

    @Enumerated(EnumType.STRING)
    private EWallet eWallet;

}
