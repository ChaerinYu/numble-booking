package com.numble.booking.user.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.user.domian.User;
import com.numble.booking.user.repository.UserRepository;
import com.numble.booking.user.value.UserSignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Class Name : UserServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-25	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-25
 */
@BookingTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void create() {
        // given
        UserSignUpDto dto = new UserSignUpDto();
        dto.setLoginId("TESTER");
        dto.setPassword("test1234!");
        dto.setPasswordCheck("test1234!");
        dto.setName("테스터");
        dto.setEmail("TESTER@TEST.COM");

        // when
        Long userId = userService.create(dto);

        // then
        assertNotNull(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        assertTrue(optionalUser.isPresent());
    }
}
