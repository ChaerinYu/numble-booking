package com.numble.booking.repository

import com.numble.booking.user.repository.UserRepository
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * <pre>
 * Class Name : UserRepositoryTest 
 * Description : 
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-23 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-23
 **/
class UserRepositoryTest {
    @Mock
    private UserRepository userRepository

    UserRepositoryTest() {
        MockitoAnnotations.openMocks(this)
    }
}
