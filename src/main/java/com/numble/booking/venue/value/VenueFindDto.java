package com.numble.booking.venue.value;

import javax.validation.constraints.Size;

import com.numble.booking.venue.type.VenuesType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : VenueFindDto
 * Description : 공연장 검색 DTO
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
public class VenueFindDto {

    @Size(max = 255, message = "공연장 이름은 255자를 넘길 수 없습니다.")
    private String name;

    // 수용인원
    private Long fromCapacity;
    private Long toCapacity;

    // 공연장 유형
    private VenuesType type;
}
