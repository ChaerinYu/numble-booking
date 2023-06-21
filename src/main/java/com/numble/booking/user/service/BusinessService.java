package com.numble.booking.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.booking.user.domian.Business;
import com.numble.booking.user.exception.UserSignUpBadRequestException;
import com.numble.booking.user.repository.BusinessRepository;
import com.numble.booking.user.repository.UserRepository;
import com.numble.booking.user.value.UserCreate;
import com.numble.booking.user.value.UserCreateDto;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : BusinessService
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-21
 */
@Service
@RequiredArgsConstructor
public class BusinessService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    /**
     * 비즈니스 계정 생성
     */
    @Transactional
    public Long create(UserCreate userCreate) {
        UserCreateDto dto = userCreate.convertToUserCreateDto(passwordEncoder);
        if (haveDuplicatedId(dto.getLoginId())) {
            throw new UserSignUpBadRequestException("이미 사용중인 아이디입니다.");
        }
        Business business = Business.create(dto);
        businessRepository.save(business);
        return business.getId();
    }

    /**
     * 중복 ID 존재 여부 조회
     */
    public boolean haveDuplicatedId(String loginId) {
        return userRepository.findByLoginId(loginId.toUpperCase()).isPresent();
    }
}
