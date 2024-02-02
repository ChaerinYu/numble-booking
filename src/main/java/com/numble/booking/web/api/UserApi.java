package com.numble.booking.web.api;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numble.booking.user.service.UserService;
import com.numble.booking.user.value.UserSignUpDto;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : UserApi
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
@Api(value = "User APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApi {
    private final UserService userService;

    @ApiOperation("회원 가입")
    @PostMapping("/sign-up")
    public Long signUp(@Valid @RequestBody UserSignUpDto dto) {
        dto.validate();
        return userService.create(dto);
    }
}
