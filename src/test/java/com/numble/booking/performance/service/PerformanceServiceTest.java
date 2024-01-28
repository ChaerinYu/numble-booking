package com.numble.booking.performance.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.performance.value.PerformanceDetailVo;
import com.numble.booking.performance.value.PerformanceFindDto;
import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.price.value.PricePolicyDto;
import com.numble.booking.seat.type.SeatType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <pre>
 * Class Name : PerformanceServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-28	    chaerin     	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-28
 */
@BookingTest
class PerformanceServiceTest {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("공연 목록 조회")
    void findAll() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);
        PerformanceFindDto dto = new PerformanceFindDto();
        dto.setVenueId(1200L);

        // when
        Page<PerformanceListVo> all = performanceService.findAll(pageRequest, dto);

        // then
        assertEquals(3, all.getContent().size());
    }

    @Test
    @DisplayName("공연 상세 조회")
    void find() {
        // given
        Long performanceId = 1100L;
        // when
        PerformanceDetailVo vo = performanceService.find(performanceId);
        // then
        assertThat(vo.getName()).isEqualTo("레미제라블");
        assertThat(vo.getGenre()).isEqualTo(Genre.MUSICAL);
        assertThat(vo.getVenueName()).isEqualTo("블루스퀘어 신한카드홀");
    }

    @Test
    @DisplayName("공연 생성")
    void create() {
        // given
        PerformanceCreateDto dto = new PerformanceCreateDto();
        dto.setName("2024 IU H.E.R. WORLD TOUR CONCERT");
        dto.setDescription("태양을 머금고 날아든 온기 새로이 움트는 모든 이들을 향해 2024년의 아이유 H. E. R. Tour를 시작합니다.");
        dto.setGenre(Genre.CONCERT);
        dto.setCapacity(50L);
        dto.setPerformanceDate(LocalDate.parse("2024-03-02"));
        dto.setStartTime(LocalTime.parse("18:00"));
        dto.setEndTime(LocalTime.parse("22:00"));
        dto.setVenueId(1300L);

        List<PricePolicyDto> policies = new ArrayList<>();
        policies.add(PricePolicyDto.builder().seatType(SeatType.PREMIUM).price(165000).build());
        policies.add(PricePolicyDto.builder().seatType(SeatType.VIP).price(154000).build());
        policies.add(PricePolicyDto.builder().seatType(SeatType.REGULAR).price(132000).build());
        dto.setPricePolicies(policies);

        // when
        Long performanceId = performanceService.create(dto);

        // then
        Performance performance = em.createQuery("SELECT p FROM Performance p WHERE p.id = :performanceId", Performance.class)
                .setParameter("performanceId", performanceId)
                .getSingleResult();
        assertThat(performance.getGenre()).isEqualTo(Genre.CONCERT);
        assertThat(performance.getVenue().getId()).isEqualTo(1300L);
        assertThat(performance.getPricePolicies().size()).isEqualTo(3);
    }
}