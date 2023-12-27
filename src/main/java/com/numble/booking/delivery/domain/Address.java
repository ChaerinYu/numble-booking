package com.numble.booking.delivery.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <pre>
 * Class Name : Address
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-12-27	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-12-27
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Column(nullable = false)
    private Long zipCode;

    @Column(nullable = false)
    private String mainAddress;

    @Column(nullable = false)
    private String detailAddress;
}
