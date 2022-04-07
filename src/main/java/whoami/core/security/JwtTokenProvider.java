package whoami.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import whoami.core.domain.members.Members;
import whoami.core.domain.members.MembersRepository;
import whoami.core.service.RedisService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequiredArgsConstructor
@Component
// NOTE : JWT를 생성하고 검증하는 컴포넌트
// NOTE : JWT를 생성하고 검증하는 컴포넌트
public class JwtTokenProvider {
    private String secretKey = "secret";
    private final MembersRepository membersRepository;

    // FIXME : 토큰 유효시간 30분 -> 나중에 10분으로 바꿔야함.
    private final long access_tokenValidTime = 1000L * 60 * 3;  // 1000L * 60 * 30; // 30분
    private final long refresh_tokenValidTime = 1000L * 60 * 5 ; // 1000 * 60 * 60 * 14; // 2주

    // NOTE : 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // NOTE : JWT Access 토큰 생성
    public String createToken(String userPk, String roles) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setHeader(headers) // header
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + access_tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                .compact();
    }

    // NOTE : JWT Refresh 토큰 생성
    public String createRefreshToken(String userPk, String roles) {
        Claims claims = Jwts.claims();
        claims.put("role", roles);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refresh_tokenValidTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    // NOTE : JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        if (this.getUserPk(token) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        System.out.println("사용자 아이디 :"+ this.getUserPk(token) + "token : " + token);
        Optional<Members> member = membersRepository.findByUserId(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(member,"",member.get().getAuthorities());
    }


    // NOTE : 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        String value = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        System.out.println("getUserPk :" + value);
        return value;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        System.out.println("resolveAccessToken : " + request.getHeader("accessToken"));
        String token = request.getHeader("accessToken");
        return token;

    }

    // NOTE : Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
    public String resolveRefreshToken(HttpServletRequest request) {
        System.out.println("resolveRefreshToken : " + request.getHeader("refreshToken"));
        String token=request.getHeader("refreshToken");
        return token;
    }

    // NOTE : 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            System.out.println("유효기간 : " + claims.getBody().getExpiration().before(new Date()));
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // NOTE : Token 남은 유효시간 (로그아웃을 위해)
    public Long getExpiration(String token) {
        Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    public boolean validateRefreshToken(String jwtToken) {
        return validateToken(jwtToken);
    }

    // NOTE : 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("accessToken", accessToken);
    }

    // NOTE : 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refreshToken", refreshToken);
    }

    // NOTE : Email로 권한 정보 가져오기
    public String getRoles(String userId) {
        return membersRepository.findByUserId(userId).get().getRole();
    }
}

