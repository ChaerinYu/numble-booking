package com.numble.booking.user.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.user.domian.Business;
import com.numble.booking.user.repository.BusinessRepository;
import com.numble.booking.user.type.BusinessType;
import com.numble.booking.user.value.BusinessSignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Class Name : BusinessServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-23	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-23
 */
@BookingTest
class BusinessServiceTest {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private BusinessRepository businessRepository;

    
    @Test
    void create() {
        // given
        BusinessSignUpDto dto = new BusinessSignUpDto();
        dto.setLoginId("BUSINESS");
        dto.setPassword("test1234!");
        dto.setPasswordCheck("test1234!");
        dto.setName("테스터");
        dto.setEmail("BUSINESS@TEST.COM");
        dto.setBusinessLicense("1234567890");
        dto.setType(BusinessType.VENUE_MANAGER);

        // when
        Long businessId = businessService.create(dto);
        
        // then
        assertNotNull(businessId);

        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        assertTrue(optionalBusiness.isPresent());
    }
}