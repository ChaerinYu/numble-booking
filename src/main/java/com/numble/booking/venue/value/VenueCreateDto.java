package com.numble.booking.venue.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.seat.value.VenueSeatDto;
import com.numble.booking.venue.type.VenuesType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : VenueCreateDto
 * Description : 공연장 등록 DTO
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
@Getter
@Setter
@NoArgsConstructor
public class VenueCreateDto {

    @NotBlank(message = "공연장 명칭을 반드시 입력 해 주세요.")
    @Size(max = 256, message = "공연장 명칭은 256자를 넘길 수 없습니다.")
    private String name;

    @NotNull(message = "수용인원 수를 반드시 입력 해 주세요.")
    @Max(value = 5000000, message = "수용인원은 500만명이 최대입니다.")
    private Long capacity;

    @NotNull(message = "공연장 유형을 선택해 주세요.")
    private VenuesType venuesType;

    @NotNull(message = "이용가능한 시작 시간을 입력 해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @NotNull(message = "이용 종료 시간을 입력 해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime closedTime;

    private List<VenueSeatDto> seats = new ArrayList<>();
}
