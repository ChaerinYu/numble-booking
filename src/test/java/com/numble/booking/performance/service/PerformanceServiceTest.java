package com.numble.booking.performance.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.numble.booking.annotation.ServiceTest;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.performance.value.PerformanceDetailVo;
import com.numble.booking.performance.value.PerformanceFindDto;
import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.price.value.PricePolicyDto;
import com.numble.booking.seat.type.SeatType;
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
@ServiceTest
class PerformanceServiceTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private PerformanceService performanceService;

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
        Long performanceId = 100001L;

        // when
        PerformanceDetailVo vo = performanceService.find(performanceId);

        // then
        assertEquals(vo.getName(), "공연A");
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