package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.type.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <pre>
 * Class Name : PerformanceListVo
 * Description : 공연 목록 VO
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
@Getter
@NoArgsConstructor
public class PerformanceListVo {

    // 공연 ID
    private Long performanceId;

    // 공연장 ID
    private Long venueId;

    // 공연장 ID
    private String venueName;

    // 공연 이름
    private String name;

    // 공연 장르
    private Genre genre;

    // 공연 일자
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate performanceDate;

    // 공연 시작 시간
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    // 공연 종료 시간
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
}
