/*
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    // JwtTokenProvider.java 컴포넌트를 이용하는 것은 인증 작업을 진행하는 Filter
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        logger.debug("doFilter 들어옴");
        try {
            // 유효한 엑세스 토큰인지 확인합니다.
            if (token != null && jwtTokenProvider.validateToken(token)) { // 유효한 access token
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.debug("Security Context에 '{}' 인증 정보 전달, url : {}",authentication.getName(),((HttpServletRequest) request).getRequestURI());
            }
            else { // 만료된 access token
                logger.debug("유효한 access 토큰이 없습니다. url : {}",((HttpServletRequest) request).getRequestURI());

            }
        }catch(Exception e){
            System.out.println("Error1: "+ e );
        }
        chain.doFilter(request,response);
    }
}*/
/*package whoami.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {// GenericFilterBean {
    // JwtTokenProvider.java 컴포넌트를 이용하는 것은 인증 작업을 진행하는 Filter
    private final JwtTokenProvider jwtTokenProvider;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // 헤더에서 JWT 를 받아옵니다.
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            try {
                // 유효한 엑세스 토큰인지 확인합니다.
                if (token != null && jwtTokenProvider.validateToken(token)) { // 유효한 access token
                    // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    System.out.println("u-----------------");
                    // SecurityContext 에 Authentication 객체를 저장합니다.
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("액세스 토큰 유효합니다" + token);
                }
//                } catch(ExpiredJwtException e){ // access 토큰 만료 시 refresh 토큰 가져오기.
//
//                }
                else { // 만료된 access token
                    String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);
                    if (refreshToken != null && jwtTokenProvider.validateRefreshToken((refreshToken))) {
                        // refreshtoken이 유효한 경우, accessToken 재발급
                        System.out.println("refreshtoken은 유효, 하지만 accesstoken 재발급 필요 ~"+ token);
                        System.out.println("현재 refreshtoken : " + refreshToken);
                        Authentication authentication = jwtTokenProvider.getAuthentication(token);
                        System.out.println("==================");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    System.out.println("다시 로그인 하세요");
                }
            }catch(Exception e){
                System.out.println("Error1: "+ e );
            }
            filterChain.doFilter(request,response);
            // 검증이 끝난 JWT로부터 유저정보를 받아와서 UsernamePasswordAuthenticationFilter
        }
}

*/
package whoami.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
    // NOTE :  JwtTokenProvider.java 컴포넌트를 이용하는 것은 인증 작업을 진행하는 Filter
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        System.out.println("accessToken (doFilter) : " + accessToken + "refresh : " + refreshToken);
        // 유효한 토큰인지 확인합니다.
        if (accessToken != null) {
            // 어세스 토큰이 유효한 상황
            if (jwtTokenProvider.validateToken(accessToken)) {
                System.out.println("액세스 토큰 유효합니다. ");
                this.setAuthentication(accessToken);
            }
            // 어세스 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
            else if (!jwtTokenProvider.validateToken(accessToken) && refreshToken != null) {
                // 재발급 후, 컨텍스트에 다시 넣기
                /// 리프레시 토큰 검증
                boolean validateRefreshToken = jwtTokenProvider.validateToken(refreshToken);
                /// 리프레시 토큰 저장소 존재유무 확인
                boolean isRefreshToken = jwtTokenProvider.existsRefreshToken(refreshToken);
                if (validateRefreshToken && isRefreshToken) {
                    /// 리프레시 토큰으로 이메일 정보 가져오기
                    String userId = jwtTokenProvider.getUserPk(refreshToken);
                    /// 이메일로 권한정보 받아오기
                    String roles = jwtTokenProvider.getRoles(userId);
                    /// 토큰 발급
                    String newAccessToken = jwtTokenProvider.createToken(userId, roles);
                    System.out.println("Access new token : " + newAccessToken);
                    /// 헤더에 어세스 토큰 추가
                    jwtTokenProvider.setHeaderAccessToken(response,newAccessToken);
                    /// 컨텍스트에 넣기
                    this.setAuthentication(newAccessToken);
                }
            }
        }
        filterChain.doFilter(request, response);
        // 검증이 끝난 JWT로부터 유저정보를 받아와서 UsernamePasswordAuthenticationFilter
        // SecurityContext 에 Authentication 객체를 저장합니다.
    }
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

