package com.numble.booking.user.domian;

import com.numble.booking.user.type.BusinessType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
public class Business extends User {

    @Column(nullable = false)
    private String businessLicense;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessType type;
}
