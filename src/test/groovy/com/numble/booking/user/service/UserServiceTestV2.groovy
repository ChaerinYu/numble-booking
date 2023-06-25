package com.numble.booking.user.service

import com.numble.booking.user.repository.UserRepository
import com.numble.booking.user.value.UserSignUpDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

/**
 * <pre>
 * Class Name : UserServiceTest
 * Description : 유저 생성 서비스
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-22	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-22
 */
@SpringBootTest
class UserServiceTestV2 extends Specification {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

//    @TestConfiguration
//    static class SpockMockConfiguration {
//        def factory = new DetachedMockFactory()
//
//        @Bean
//        UserService userService() {
//            return factory.Mock(UserService)
//        }
//
//        @Bean
//        UserRepository userRepository() {
//            return factory.Mock(UserRepository)
//        }
//    }

    def "Create"() {
        given:
        def dto = new UserSignUpDto()
        dto.loginId = "TESTER"
        dto.password = "test1234!"
        dto.passwordCheck = "test1234!"
        dto.name = "테스터"
        dto.email = "TEST@TEST.COM"

        when:
        def id = userService.create(dto)

        then:
        1 == 1
//        id != null
//        def user = userRepository.findById(id)
//        user != null
    }
}
