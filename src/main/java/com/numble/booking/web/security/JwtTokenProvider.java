package com.numble.booking.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.security.domain.CustomUser;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <pre>
 * Class Name : JwtTokenProvider
 * Description : spring security JWT token provider
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-31 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-31
 */
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final ObjectMapper objectMapper;

    public final static String TOKEN_NAME = "X-AUTH-TOKEN";
    public final static long TOKEN_VALID_MILISECOND = 1000L * 60L * 60L * 2L; // 2시간만 토큰 유효
    private String secretKey = "secret";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    // Jwt 토큰 생성
    public String createToken(CustomUser cu, HttpServletRequest request) {
        Claims claims = Jwts.claims().setSubject(cu.getUsername());

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_MILISECOND)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compressWith(CompressionCodecs.DEFLATE)
                .compact();
    }

    // Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String json = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        UserVo userVo = objectMapper.readValue(json, UserVo.class);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        CustomUser cu = new CustomUser(userVo.getLoginId(), "", grantedAuthorities, userVo);
        return new UsernamePasswordAuthenticationToken(cu, "", grantedAuthorities);
    }

    // token parsing
    public String resolveToken(HttpServletRequest req) {
        String token;
        if (req.getCookies() != null) {
            Optional<Cookie> tokenCookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(TOKEN_NAME))
                    .findAny();
            token = tokenCookie.map(Cookie::getValue).orElse(null);
        } else {
            token = req.getHeader(TOKEN_NAME);
        }
        log.debug("request token {}", token);
        return token;
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {

        boolean result;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            // 계열사 api 일 경우 ip 체크 로직을 타지 않음
            result = !claims.getBody().getExpiration().before(new Date());

            return result;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenName(){
        return TOKEN_NAME;
    }
}
