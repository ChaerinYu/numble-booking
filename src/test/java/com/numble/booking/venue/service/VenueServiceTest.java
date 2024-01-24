package com.numble.booking.venue.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.repository.SeatRepository;
import com.numble.booking.seat.type.SeatType;
import com.numble.booking.seat.value.VenueSeatDto;
import com.numble.booking.venue.domain.Venue;
import com.numble.booking.venue.exception.NotFoundVenueException;
import com.numble.booking.venue.repository.VenueRepository;
import com.numble.booking.venue.type.VenuesType;
import com.numble.booking.venue.value.VenueCreateDto;
import com.numble.booking.venue.value.VenueDetailVo;
import com.numble.booking.venue.value.VenueFindDto;
import com.numble.booking.venue.value.VenueListVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * Class Name : VenueServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-25	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-25
 */
@BookingTest
class VenueServiceTest {

    @Autowired
    private VenueService venueService;
    
    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private SeatRepository seatRepository;
    
    @Test
    void create() {
        // given
        VenueCreateDto dto = new VenueCreateDto();
        dto.setName("올림픽 체조 경기장");
        dto.setCapacity(10000L);
        dto.setVenuesType(VenuesType.FIXED_SEAT);
        dto.setOpenTime(LocalTime.NOON);
        dto.setClosedTime(LocalTime.MIDNIGHT);
        List<VenueSeatDto> seats = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            VenueSeatDto seat = new VenueSeatDto();
            int number = i % 100 + 1;
            char alphabet = (char) ('A' + (int) Math.floor(i / 100));
            seat.setSeatNumber(String.valueOf(alphabet)+ number);
            if (alphabet == 'A') {
                seat.setSeatType(SeatType.VIP);
            } else {
                seat.setSeatType(SeatType.REGULAR);
            }
            seats.add(seat);
        }
        dto.setSeats(seats);

        // when
        Long venueId = venueService.create(dto);

        // then
        Venue venue = venueRepository.findById(venueId).orElseThrow(NotFoundVenueException::new);
        assertEquals(venue.getName(), "올림픽 체조 경기장");

        List<Seat> venueSeats = seatRepository.findByVenue(venueId);
        assertEquals(1000, venueSeats.size());
        assertFalse(venueSeats.stream().filter(vs -> vs.getSeatNumber().equals("B10")).findAny().isEmpty());
    }

    @Test
    @DisplayName("공연장 목록 조회")
    void findAll() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);
        VenueFindDto dto = new VenueFindDto();
        dto.setType(VenuesType.FIXED_SEAT);

        // when
        Page<VenueListVo> venues = venueService.findAll(pageRequest, dto);

        // then
        for (VenueListVo venue : venues) {
            System.out.println("venue info: " + venue.getName() + ", times: " + venue.getPossibleTimes());
        }

        assertEquals(2, venues.getContent().size());
    }
    
    @Test
    @DisplayName("공연장 조회")
    void find() {
        // given
        Long venueId = 1000L;
        // when
        VenueDetailVo vo = venueService.find(venueId);
        // then
        assertEquals(vo.getCapacity(), 10L);
    }
}