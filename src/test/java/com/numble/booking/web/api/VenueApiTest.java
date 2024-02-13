package com.numble.booking.web.api;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.annotation.BookingTest;
import com.numble.booking.seat.type.SeatType;
import com.numble.booking.seat.value.VenueSeatDto;
import com.numble.booking.venue.type.VenuesType;
import com.numble.booking.venue.value.VenueCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : VenueApiTest
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
class VenueApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final String API = "/venues";

    @Test
    @WithUserDetails("USER1")
    @DisplayName("공연장 목록 조회 paging")
    void findAll() throws Exception {
        // given
        String name = "KSPO";
        // when
        ResultActions result = mockMvc.perform(get(API)
                .param("name", name));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].venueId", equalTo(1300)))
                .andExpect(jsonPath("$.content[0].name", equalTo("KSPO DOME")))
                .andExpect(jsonPath("$.content[0].capacity", equalTo(50)))
                .andDo(print());
    }

    @Test
    @WithUserDetails("USER1")
    @DisplayName("공연장 상세 조회")
    void find() throws Exception {
        // given
        Long venueId = 1300L;
        // when
        ResultActions result = mockMvc.perform(get(API + "/" + venueId));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.venueId", equalTo(1300)))
                .andExpect(jsonPath("$.name", equalTo("KSPO DOME")))
                .andExpect(jsonPath("$.capacity", equalTo(50)))
                .andDo(print());
    }

    @Test
    @WithUserDetails("ADMIN")
    @DisplayName("공연장 생성")
    void create() throws Exception {
        // given
        VenueCreateDto dto = new VenueCreateDto();
        dto.setName("TEST VENUE");
        dto.setCapacity(3L);
        dto.setVenuesType(VenuesType.LOUNGE);
        dto.setOpenTime(LocalTime.MIN);
        dto.setClosedTime(LocalTime.MAX);
        List<VenueSeatDto> seats = new ArrayList<>();
        for (int i=0; i<3; i++) {
            VenueSeatDto seat = new VenueSeatDto();
            seat.setSeatNumber((i+1) + "번");
            seat.setSeatType(SeatType.REGULAR);
            seats.add(seat);
        }
        dto.setSeats(seats);
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