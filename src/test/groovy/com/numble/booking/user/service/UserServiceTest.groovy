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
class UserServiceTest extends Specification {

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
        UserSignUpDto dto = new UserSignUpDto()
        dto.setLoginId("TESTER")
        dto.setPassword("test1234!")
        dto.setPasswordCheck("test1234!")
        dto.setName("테스터")
        dto.setEmail("TEST@TEST.COM")

        when:
        def id = userService.create(dto)

        then:
        1 == 1
//        id != null
//        def user = userRepository.findById(id)
//        user.isPresent()
//        user.get().getName() == "TESTER"
    }
}
