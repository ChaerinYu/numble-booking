package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.price.value.PricePolicyDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("공연 생성 DTO")
@Getter
@Setter
@NoArgsConstructor
public class PerformanceCreateDto {

    @ApiModelProperty(value = "공연 이름", notes = "공연 명칭은 255자를 넘길 수 없습니다.")
    @NotBlank(message = "공연 명칭을 반드시 입력해 주세요.")
    @Size(max = 255, message = "공연 명칭은 255자를 넘길 수 없습니다.")
    private String name;

    @ApiModelProperty(value = "공연 설명", notes = "공연 설명은 1000자를 넘길 수 없습니다.")
    @Size(max = 1000, message = "공연 설명은 1000자를 넘길 수 없습니다.")
    private String description;

    @ApiModelProperty(value = "공연 장르", example = "MUSICAL")
    @NotNull(message = "공연 장르를 선택해 주세요.")
    private Genre genre;

    @ApiModelProperty(value = "수용인원", notes = "default는 Venue 의 capacity와 동일")
    @NotNull(message = "수용인원을 입력해 주세요.")
    private Long capacity;

    @ApiModelProperty(value = "공연 일자", example = "2024-01-01")
    @NotNull(message = "공연 일자를 입력해 주세요.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate performanceDate;

    @ApiModelProperty(value = "공연 시작 시간", example = "13:00")
    @NotNull(message = "공연 시작시간을 입력해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @ApiModelProperty(value = "공연 종료 시간", example = "15:00")
    @NotNull(message = "공연 종료시간을 입력해 주세요.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @ApiModelProperty("공연장 ID")
    @NotNull(message = "공연장을 선택해 주세요.")
    private Long venueId;

    @ApiModelProperty("공연 가격 정책")
    @NotNull(message = "공연 가격을 입력해 주세요.")
    private List<PricePolicyDto> pricePolicies = new ArrayList<>();
}
