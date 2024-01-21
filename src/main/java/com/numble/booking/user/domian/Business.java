package com.numble.booking.user.domian;

import java.time.LocalDateTime;

import com.numble.booking.user.type.BusinessType;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Business
 * Description : 비즈니스 계정
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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("BUSINESS")
public class Business extends User {

    @Column(nullable = false)
    private String businessLicense;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessType type;

    /**
     * 생성
     */
    public static Business create(UserCreateDto dto) {
        LocalDateTime now = LocalDateTime.now();

        Business entity = new Business();
        entity.password = dto.getPassword();
        entity.loginId = dto.getLoginId().toUpperCase();
        entity.name = dto.getName();
        entity.email = dto.getEmail();
        entity.status = UserStatus.ACTIVE;
        entity.lastPasswordModifyDate = now;
        entity.businessLicense = dto.getBusinessLicense();
        entity.type = dto.getType();

        return entity;
    }
}
