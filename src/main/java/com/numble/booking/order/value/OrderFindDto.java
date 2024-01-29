package com.numble.booking.order.value;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;

import com.numble.booking.common.base.MemberUser;
import com.numble.booking.delivery.type.DeliveryStatus;
import com.numble.booking.order.type.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * <pre>
 * Class Name : OrderFindDto
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-29 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-29
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderFindDto {

    @NotNull(message = "사용자 ID 값이 없습니다.", groups = {MemberUser.class, Default.class})
    @ApiModelProperty(value = "사용자 id")
    private Long userId;

    @Length(max = 50, message = "사용자 명은 50자 이하입니다.")
    @ApiModelProperty(value = "사용자 명")
    private String username;

    @ApiModelProperty(value = "주문 상태")
    private OrderStatus orderStatus;

    @ApiModelProperty(value = "배송 상태")
    private DeliveryStatus deliveryStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
}
