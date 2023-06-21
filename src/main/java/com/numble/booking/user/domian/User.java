package com.numble.booking.user.domian;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : User
 * Description : 사용자
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
@Table(name = "users")
public class User extends CreatedAndModifiedBase {
    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(generator = "userSeqGenerator")
    @GenericGenerator(name = "userSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    protected Long id;

    @Column(name = "loginId", length = 12, nullable = false, unique = true)
    protected String loginId;

    // 이름
    @Column(nullable = false, length = 50)
    protected String name;

    // 비밀번호
    @Column(nullable = false)
    protected String password;

    // 이메일
    @Column(nullable = false)
    protected String email;

    // 유저 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected UserStatus status;

    // 마지막 비밀번호 수정일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    protected LocalDateTime lastPasswordModifyDate = LocalDateTime.now();

    // 마지막 로그인 일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    protected LocalDateTime lastLoginDate;

    // 사용자가 갖고 있는 쿠폰
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons = new ArrayList<>();

    /**
     * 생성
     */
    public static User create(UserCreateDto dto) {
        LocalDateTime now = LocalDateTime.now();

        User entity = new User();
        entity.password = dto.getPassword();
        entity.loginId = dto.getLoginId().toUpperCase();
        entity.name = dto.getName();
        entity.email = dto.getEmail();
        entity.status = UserStatus.ACTIVE;
        entity.lastPasswordModifyDate = now;
        // TODO 첫 가입 쿠폰

        return entity;
    }
}
