package com.numble.booking.performance.value;

import com.numble.booking.performance.type.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("공연 검색 DTO")
@Getter
@Setter
@NoArgsConstructor
public class PerformanceFindDto {

    @ApiModelProperty("공연장 ID")
    private Long venueId;

    @ApiModelProperty(value = "공연 이름", notes = "공연 명칭은 255자를 넘길 수 없습니다.")
    @Size(max = 255, message = "공연 명칭은 255자를 넘길 수 없습니다.")
    private String name;

    @ApiModelProperty(value = "공연 장르", example = "MUSICAL")
    private Genre genre;

    @ApiModelProperty(value = "공연 일자 from", example = "2024-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @ApiModelProperty(value = "공연 일자 to", example = "2024-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
}
