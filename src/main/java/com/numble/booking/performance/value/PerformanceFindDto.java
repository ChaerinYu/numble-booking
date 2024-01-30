package com.numble.booking.performance.value;

import com.numble.booking.performance.type.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.validation.constraints.Size;

/**
 * <pre>
 * Class Name : PerformanceFindDto
 * Description : 공연 검색 DTO
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
@Setter
@NoArgsConstructor
public class PerformanceFindDto {

    private Long venueId;

    @Size(max = 255, message = "공연 명칭은 255자를 넘길 수 없습니다.")
    private String name;

    private Genre genre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
}
