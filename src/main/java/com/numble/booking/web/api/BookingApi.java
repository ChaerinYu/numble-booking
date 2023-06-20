package com.numble.booking.web.api;

import com.numble.booking.book.service.BookingService;
import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.common.base.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <pre>
 * Class Name : BookingApi
 * Description : 예약 api
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingApi {

    private final BookingService bookingService;

    /**
     * 공연 예약
     * 첫 단계 : 공연과 좌석을 선택한다.
     */
    @PostMapping("/1")
    public MessageVo bookingFirstStep(@Valid @RequestBody BookingFirstDto dto) {
        Long performanceId = bookingService.bookingFirstStep(dto);
        return new MessageVo(HttpStatus.OK, performanceId);
    }

    /**
     * 공연 예약
     * 두 번째 단계 : 선택된 공연 좌석 결제를 진행한다.
     */
    @PostMapping("/2")
    public MessageVo bookingSecondStep(@Valid @RequestBody BookingSecondDto dto) {
        bookingService.bookingSecondStep(dto);
        return new MessageVo(HttpStatus.OK, "결제되었습니다.");
    }
}
