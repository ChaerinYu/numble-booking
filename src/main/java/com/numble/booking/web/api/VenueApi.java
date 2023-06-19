package com.numble.booking.web.api;

import com.numble.booking.venue.repository.VenueQuerydslRepository;
import com.numble.booking.venue.service.VenueService;
import com.numble.booking.venue.value.VenueCreateDto;
import com.numble.booking.venue.value.VenueDetailVo;
import com.numble.booking.venue.value.VenueFindDto;
import com.numble.booking.venue.value.VenueListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * Class Name : VenueApi
 * Description : 공연장 API
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
@RequestMapping("/venues")
public class VenueApi {

    private final VenueService venueService;

    /**
     * 공연장 목록 조회
     */
    @GetMapping
    public Page<VenueListVo> findAll(Pageable pageable, VenueFindDto dto) {
        return venueService.findAll(pageable, dto);
    }

    /**
     * 공연장 조회
     */
    @GetMapping("/{venueId}")
    public VenueDetailVo find(@PathVariable Long venueId) {
        return venueService.find(venueId);
    }

    /**
     * 공연장 등록
     */
    @PostMapping
    public Long create(@Valid @RequestBody VenueCreateDto dto) {
        return venueService.create(dto);
    }
}
