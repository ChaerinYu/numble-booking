package com.numble.booking.web.api;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.annotation.BookingTest;
import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.delivery.value.DeliveryCreateDto;
import com.numble.booking.seat.value.SeatBookingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : BookingApiTest
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
@BookingTest
class BookingApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final String API = "/bookings";

    @Test
    @WithUserDetails("USER1")
    @DisplayName("공연과 좌석을 선택")
    void bookingFirstStep() throws Exception {
        // given
        BookingFirstDto dto = new BookingFirstDto();
        dto.setPerformanceId(1100L);
        List<SeatBookingDto> seats = new ArrayList<>();
        seats.add(SeatBookingDto.builder().performanceSeatId(110005L).seatNumber("6").build());
        seats.add(SeatBookingDto.builder().performanceSeatId(110006L).seatNumber("7").build());
        dto.setSeats(seats);
        dto.setUserId(1001L); // TODO 방식 변경하기
        // when
        ResultActions result = mockMvc.perform(post(API + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus", equalTo(200)))
                .andExpect(jsonPath("$.contents", equalTo(1100)))
                .andDo(print());
    }

    @Test
    @WithUserDetails("USER3")
    @DisplayName("선택된 공연 좌석 결제를 진행")
    void bookingSecondStep() throws Exception {
        // given
        BookingSecondDto dto = new BookingSecondDto();
        dto.setPerformanceId(1000L);
        DeliveryCreateDto deliveryDto = new DeliveryCreateDto();
        deliveryDto.setReceiverName("유채린");
        deliveryDto.setPhone("010-1231-1231");
        deliveryDto.setZipCode(12341L);
        deliveryDto.setMainAddress("경기도 경기시");
        deliveryDto.setDetailAddress("경기도 1234");
        dto.setDeliveryDto(deliveryDto);
        dto.setUserId(1003L);
        // when
        ResultActions result = mockMvc.perform(post(API + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus", equalTo(200)))
                .andExpect(jsonPath("$.message", equalTo("결제되었습니다.")))
                .andDo(print());
    }
}