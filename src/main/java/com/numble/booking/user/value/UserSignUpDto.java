package com.numble.booking.user.value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.numble.booking.user.exception.UserSignUpBadRequestException;
import com.numble.booking.util.MapperUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * <pre>
 * Class Name : UserSignUpDto
 * Description : 회원가입 DTO
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
@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDto implements UserCreate {
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
            message = "영문, 숫자, 특수문자를 포함하여 8자리 이상 16자 이하로 입력해주세요.")
    private String password;

    // password와 동일해야 함
    private String passwordCheck;

    @Length(max = 12, message = "로그인ID는 12자 이하입니다.")
    private String loginId;

    @NotBlank(message = "이름을 입력해 주세요. ")
    @Pattern(regexp = "^[가-힣A-za-z]{1,10}$", message = "이름은 한글/영문 포함하여 10자리 이하입니다.")
    private String name;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 아닙니다.")
    private String email;

    /**
     * 검증
     */
    public void validate() {
        if (!password.equals(passwordCheck)) {
            throw new UserSignUpBadRequestException("비밀번호를 다시 입력해 주세요.");
        }
    }

    @Override
    public UserCreateDto convertToUserCreateDto(PasswordEncoder encoder) {
        UserCreateDto createDto = MapperUtil.map(this, UserCreateDto.class);
        createDto.setPassword(encoder.encode(createDto.getPassword()));
        return createDto;
    }
}
