package com.numble.booking.web.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.annotation.BookingTest;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.price.value.PricePolicyDto;
import com.numble.booking.seat.type.SeatType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : PerformanceApiTest
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
class PerformanceApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final String API = "/performances";

    @Test
    @DisplayName("공연 목록 조회 paging")
    void findAll() throws Exception {
        // given
        Long venueId = 1200L;
        // when
        ResultActions result = mockMvc.perform(get(API)
                .param("venueId", String.valueOf(venueId))
                .param("name", "레베카"));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].performanceId", equalTo(1200)))
                .andExpect(jsonPath("$.content[0].venueId", equalTo(1200)))
                .andExpect(jsonPath("$.content[0].venueName", equalTo("LG SIGNATURE 홀")))
                .andExpect(jsonPath("$.content[0].performanceDate", equalTo("2024-02-01")))
                .andExpect(jsonPath("$.content[0].startTime", equalTo("19:00:00")))
                .andExpect(jsonPath("$.content[0].endTime", equalTo("21:00:00")))
                .andDo(print());
    }

    @Test
    @DisplayName("공연 상세 조회")
    void find() throws Exception {
        // given
        Long performanceId = 1200L;
        // when
        ResultActions result = mockMvc.perform(get(API + "/" + performanceId));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.performanceId", equalTo(1200)))
                .andExpect(jsonPath("$.name", equalTo("레베카")))
                .andExpect(jsonPath("$.genre", equalTo(Genre.MUSICAL.name())))
                .andDo(print());
    }

    @Test
    @DisplayName("남아있는 공연 좌석 조회")
    void findRemainingSeats() throws Exception {
        // given
        Long performanceId = 1000L;
        // when
        ResultActions result = mockMvc.perform(get(API + "/" + performanceId + "/seats"));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.performanceId", equalTo(1000)))
                .andExpect(jsonPath("$.seats.size()", equalTo(2)))
                .andDo(print());
    }

    @Test
    @DisplayName("공연 생성")
    void create() throws Exception {
        // given
        PerformanceCreateDto dto = new PerformanceCreateDto();
        dto.setName("TEST PERFORMANCE");
        dto.setDescription("TEST DESCRIPTION");
        dto.setGenre(Genre.OPERA);
        dto.setCapacity(5L);
        dto.setPerformanceDate(LocalDate.now());
        dto.setStartTime(LocalTime.of(13, 0));
        dto.setEndTime(LocalTime.of(15, 0));
        dto.setVenueId(1000L);
        List<PricePolicyDto> pricePolicies = new ArrayList<>();
        pricePolicies.add(PricePolicyDto.builder().seatType(SeatType.REGULAR).price(100000).build());
        dto.setPricePolicies(pricePolicies);
        // when
        ResultActions result = mockMvc.perform(post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus", equalTo(200)))
                .andExpect(jsonPath("$.contents", notNullValue()))
                .andExpect(jsonPath("$.message", equalTo("등록되었습니다.")))
                .andDo(print());
    }
}