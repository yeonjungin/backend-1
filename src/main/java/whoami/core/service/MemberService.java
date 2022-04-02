package whoami.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whoami.core.domain.Role;
import whoami.core.domain.members.Members;
import whoami.core.domain.members.MembersRepository;
import whoami.core.dto.members.LoginRequestDto;
import whoami.core.dto.members.LoginResponseDto;
import whoami.core.dto.members.MembersSaveRequestDto;
import whoami.core.dto.members.MembersUpdateRequestDto;
import whoami.core.security.JwtTokenProvider;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MembersRepository membersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    // NOTE : 스프링 시큐리티에서 유저를 찾는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return membersRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.1"));
    }

    // NOTE : 회원가입 처리
    @Transactional
    public Long joinMember(MembersSaveRequestDto requestDto) {
        // 중복 회원 검증
        if (!validateDuplicateMember(requestDto)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

            // FIXME --> 이대로 고치기
            if (Objects.equals(requestDto.getRole(), "ADMIN")){
                requestDto.setRole(Role.ADMIN.getValue());
            } else{
                requestDto.setRole(Role.USER.getValue());
            } //

            return membersRepository.save(requestDto.toEntity()).getMemberId();
        }
        return null;
    }

    // NOTE : 로그인
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        Optional<Members> members = membersRepository.findByUserId(loginRequestDto.getUserId());
        Members member = membersRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원아이디 입니다."));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // FIXME : 이대로 고치기!
        String accessToken = jwtTokenProvider.createToken(loginRequestDto.getUserId(),members.get().getRole());
        String refreshToken=jwtTokenProvider.createRefreshToken(loginRequestDto.getUserId(),members.get().getRole());

        // refreshToken redis db에 저장
        redisService.setValues(refreshToken, loginRequestDto.getUserId());
        return new LoginResponseDto(accessToken,refreshToken);
    }


    // NOTE : 회원 조회
    @Transactional
    public Members findById(Long id){
        Members entity=membersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다. id="+id));
        return entity;
    }

    // NOTE : 회원 리스트 조회
    @Transactional
    public List<Members> listMembers(){
        List<Members> members=membersRepository.findAll();
        return members;
    }

    // NOTE : 회원 정보 수정
    @Transactional
    public Long update(MembersUpdateRequestDto requestDto){
        Members members=membersRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        members.update(requestDto.getPassword(),requestDto.getPhoneNum(),requestDto.getEmail(),requestDto.isReceiveNotification());
        return members.getMemberId();
    }

    // NOTE : 회원가입 아이디 중복체크
    @Transactional
    public boolean validateDuplicateMember(MembersSaveRequestDto membersDto){
        return membersRepository.existsByUserId(membersDto.getUserId());
    }
}
