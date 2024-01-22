package com.numble.booking.venue.service;

import com.numble.booking.performance.repository.PerformanceQuerydslRepository;
import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.repository.SeatRepository;
import com.numble.booking.seat.value.VenueSeatDto;
import com.numble.booking.seat.value.VenueSeatListVo;
import com.numble.booking.venue.domain.Venue;
import com.numble.booking.venue.exception.NotFoundVenueException;
import com.numble.booking.venue.repository.VenueQuerydslRepository;
import com.numble.booking.venue.repository.VenueRepository;
import com.numble.booking.venue.value.VenueCreateDto;
import com.numble.booking.venue.value.VenueDetailVo;
import com.numble.booking.venue.value.VenueFindDto;
import com.numble.booking.venue.value.VenueListVo;
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
 * Class Name : VenueService
 * Description :
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
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueQuerydslRepository venueQuerydslRepository;
    private final PerformanceQuerydslRepository performanceQuerydslRepository;
    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Page<VenueListVo> findAll(Pageable pageable, VenueFindDto dto) {
        return venueQuerydslRepository.findAll(pageable, dto);
    }

    @Transactional(readOnly = true)
    public VenueDetailVo find(Long venueId) {
        VenueDetailVo vo = venueQuerydslRepository.find(venueId);
        if (vo == null) {
            throw new NotFoundVenueException();
        }
        vo.setSeats(seatRepository.findByVenue(venueId)
                .stream()
                .map(VenueSeatListVo::of)
                .collect(Collectors.toList()));
        vo.setPerformances(performanceQuerydslRepository.findAll(venueId));
        return vo;
    }

    @Transactional
    public Long create(VenueCreateDto dto) {
        Venue venue = Venue.create(dto);
        List<Seat> seats = new ArrayList<>();
        for (VenueSeatDto seat : dto.getSeats()) {
            seats.add(Seat.create(seat.getSeatNumber(), seat.getSeatType(), venue));
        }
        venue.setSeats(seats);
        venueRepository.save(venue);

        return venue.getId();
    }
}
