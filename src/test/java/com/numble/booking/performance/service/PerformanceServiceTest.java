package com.numble.booking.performance.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.performance.value.PerformanceDetailVo;
import com.numble.booking.performance.value.PerformanceFindDto;
import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.price.value.PricePolicyDto;
import com.numble.booking.seat.type.SeatType;
import com.numble.booking.seat.value.VenueSeatDto;
import com.numble.booking.venue.domain.Venue;
import com.numble.booking.venue.repository.VenueRepository;
import com.numble.booking.venue.service.VenueService;
import com.numble.booking.venue.type.VenuesType;
import com.numble.booking.venue.value.VenueCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : PerformanceServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-26 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-26
 */
@SpringBootTest
class PerformanceServiceTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private VenueService venueService;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private PerformanceService performanceService;


    private Venue setupVenue;
    private Performance setupPerformance;

//    @BeforeEach
    public void setUp() {
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

        // venue 저장
        Long venueId = venueService.create(dto);
        setupVenue = em.find(Venue.class, venueId);

        PerformanceCreateDto performanceDto = new PerformanceCreateDto();
        performanceDto.setName("찰리 푸스 내한 공연");
        performanceDto.setDescription("내한 공연입니다.");
        performanceDto.setGenre(Genre.CONCERT);
        performanceDto.setCapacity(1000L);
        performanceDto.setDate(LocalDate.of(2023, 10, 21));
        performanceDto.setStartTime(LocalTime.of(19, 0));
        performanceDto.setEndTime(LocalTime.of(0, 0));
        performanceDto.setVenueId(venueId);
        List<PricePolicyDto> prices = new ArrayList<>();
        prices.add(PricePolicyDto.builder().type(SeatType.VIP).price(160000).build());
        prices.add(PricePolicyDto.builder().type(SeatType.REGULAR).price(100000).build());
        performanceDto.setTicketPrices(prices);

        // performance 저장
        Long performanceId = performanceService.create(performanceDto);
        setupPerformance = em.find(Performance.class, performanceId);
    }

    @Test
    void findAll() {
        // given // when
        PageRequest page = PageRequest.of(0, 10);
        PerformanceFindDto dto = new PerformanceFindDto();
        Page<PerformanceListVo> all = performanceService.findAll(page, dto);

        // then
        assertNotNull(all.getContent());
    }

    @Test
    void find() {
        // given
//        Long performanceId = setupPerformance.getId();
        Long performanceId = 100001L;

        // when
        PerformanceDetailVo vo = performanceService.find(performanceId);

        // then
        assertEquals(vo.getName(), "찰리 푸스 내한 공연");
    }

    @Test
    void create() {
        // given
        PerformanceCreateDto dto = new PerformanceCreateDto();
        dto.setName("브루노 마스 내한 공연");
        dto.setDescription("9년만의 내한 공연입니다.");
        dto.setGenre(Genre.CONCERT);
        dto.setCapacity(1000L);
        dto.setDate(LocalDate.of(2023, 6, 17));
        dto.setStartTime(LocalTime.of(20, 0));
        dto.setEndTime(LocalTime.of(0, 0));
//        dto.setVenueId(setupVenue.getId());
        dto.setVenueId(100001L);
        List<PricePolicyDto> prices = new ArrayList<>();
        prices.add(PricePolicyDto.builder().type(SeatType.VIP).price(160000).build());
        prices.add(PricePolicyDto.builder().type(SeatType.REGULAR).price(100000).build());
        dto.setTicketPrices(prices);

        // when
        Long performanceId = performanceService.create(dto);

        // then
        Performance performance = em.find(Performance.class, performanceId);
        assertEquals(performance.getCapacity(), 1000L);
    }
}