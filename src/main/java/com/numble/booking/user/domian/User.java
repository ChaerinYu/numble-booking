package com.numble.booking.user.domian;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.user.type.RoleType;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@DiscriminatorValue("USER")
@Table(name = "users")
public class User extends CreatedAndModifiedBase implements Serializable {
    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(generator = "userSeqGenerator")
    @GenericGenerator(name = "userSeqGenerator", strategy = "com.numble.booking.common.util.SeqGenerator")
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected RoleType roleType;

    // 마지막 비밀번호 수정일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    protected LocalDateTime lastPasswordModifyDate = LocalDateTime.now();

    // 마지막 로그인 일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    protected LocalDateTime lastLoginDate;

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
        entity.roleType = RoleType.MEMBER;
        entity.lastPasswordModifyDate = now;

        return entity;
    }

    /**
     * 마지막 로그인일시 업데이트
     */
    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
