package com.numble.booking.performance.service;

import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.performance.exception.NotFoundPerformanceException;
import com.numble.booking.performance.repository.PerformanceQuerydslRepository;
import com.numble.booking.performance.repository.PerformanceRepository;
import com.numble.booking.performance.repository.PerformanceSeatRepository;
import com.numble.booking.performance.value.PerformanceCreateDto;
import com.numble.booking.performance.value.PerformanceDetailVo;
import com.numble.booking.performance.value.PerformanceFindDto;
import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.price.domain.PricePolicy;
import com.numble.booking.price.repository.PricePolicyRepository;
import com.numble.booking.price.value.PricePolicyDto;
import com.numble.booking.price.value.PricePolicyVo;
import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.repository.SeatRepository;
import com.numble.booking.util.MapperUtil;
import com.numble.booking.venue.domain.Venue;
import com.numble.booking.venue.exception.NotFoundVenueException;
import com.numble.booking.venue.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Class Name : PerformanceService
 * Description : 공연 서비스
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final PerformanceQuerydslRepository performanceQuerydslRepository;
    private final PricePolicyRepository pricePolicyRepository;
    private final VenueRepository venueRepository;
    private final PerformanceRepository performanceRepository;
    private final PerformanceSeatRepository performanceSeatRepository;
    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Page<PerformanceListVo> findAll(Pageable pageable, PerformanceFindDto dto) {
        return performanceQuerydslRepository.findAll(pageable, dto);
    }

    @Transactional(readOnly = true)
    public PerformanceDetailVo find(Long performanceId) {
        Performance performance = performanceRepository.findById(performanceId).orElseThrow(NotFoundPerformanceException::new);
        // 공연 좌석별 금액 조회
        List<PricePolicyVo> pricePolicies = pricePolicyRepository.findByPerformance(performanceId)
                .stream()
                .map(pp -> MapperUtil.map(pp, PricePolicyVo.class))
                .collect(Collectors.toList());
        return PerformanceDetailVo.of(performance, pricePolicies);
    }

    @Transactional
    public Long create(PerformanceCreateDto dto) {
        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(NotFoundVenueException::new);

        Performance performance = performanceRepository.save(Performance.create(venue, dto));

        // 공연 좌석 가격 생성
        List<PricePolicy> prices = new ArrayList<>();
        for (PricePolicyDto ticketPrice : dto.getTicketPrices()) {
            prices.add(PricePolicy.create(performance, ticketPrice.getType(), ticketPrice.getPrice()));
        }
        performance.setPricePolicies(prices);

        // 공연 좌석 이용가능 update
        List<Seat> seats = seatRepository.findByVenue(venue.getId());
        List<PerformanceSeat> performanceSeats = new ArrayList<>();
        for (Seat seat : seats) {
            performanceSeats.add(PerformanceSeat.create(performance, seat));
        }
        performance.setPerformanceSeats(performanceSeats);

        return performance.getId();
    }
}
