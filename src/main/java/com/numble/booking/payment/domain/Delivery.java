package com.numble.booking.payment.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.payment.value.DeliveryCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Delivery
 * Description : 배송지
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
public class Delivery extends CreatedAndModifiedBase {

    @Id
    @Column(name = "deliveryId", nullable = false)
    @GeneratedValue(generator = "deliverySeqGenerator")
    @GenericGenerator(name = "deliverySeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 수령인
    @Column(nullable = false)
    private String receiverName;

    // 휴대폰 번호
    @Column(nullable = false)
    private String phone;

    // 배송지 주소
    @Column(nullable = false)
    private Long zipCode;

    @Column(nullable = false)
    private String mainAddress;

    @Column(nullable = false)
    private String detailAddress;
    
    // 배송 메시지
    private String message;

    public static Delivery of(DeliveryCreateDto dto) {
        Delivery entity = new Delivery();
        entity.receiverName = dto.getReceiverName();
        entity.phone = dto.getPhone();
        entity.zipCode = dto.getZipCode();
        entity.mainAddress = dto.getMainAddress();
        entity.detailAddress = dto.getDetailAddress();
        entity.message = dto.getMessage();
        return entity;
    }
}
