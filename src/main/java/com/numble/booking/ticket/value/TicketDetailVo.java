package com.numble.booking.ticket.value;

import com.numble.booking.ticket.type.TicketStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : TicketDetailVo
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-30 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-30
 */
@ApiModel("티켓 상세")
@Getter
@NoArgsConstructor
public class TicketDetailVo {
    @ApiModelProperty(value = "티켓 번호")
    private String ticketKey;

    @ApiModelProperty(value = "공연")
    private TicketStatus ticketStatus;

    @ApiModelProperty(value = "좌석")
    private String seatNumber;

    @ApiModelProperty(value = "공연 id")
    private Long performanceId;

    @ApiModelProperty(value = "공연 명")
    private String performanceName;
}
