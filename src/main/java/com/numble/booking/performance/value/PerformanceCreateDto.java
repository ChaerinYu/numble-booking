package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.price.value.PricePolicyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : PerformanceCreateDto
 * Description : 공연 등록 DTO
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
public class PerformanceCreateDto {

    @NotBlank(message = "공연 명칭을 반드시 입력해 주세요.")
    @Size(max = 256, message = "공연 명칭은 256자를 넘길 수 없습니다.")
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "공연 장르를 선택해 주세요.")
    private Genre genre;

    // default는 Venue 의 capacity와 동일
    @NotNull(message = "수용인원을 입력해 주세요.")
    private Long capacity;

    @NotNull(message = "공연 일자를 입력해 주세요.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "공연 시작시간을 입력해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "공연 종료시간을 입력해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotNull(message = "공연장을 선택해 주세요.")
    private Long venueId;

    @NotNull(message = "공연 가격을 입력해 주세요.")
    private List<PricePolicyDto> ticketPrices = new ArrayList<>();
}
