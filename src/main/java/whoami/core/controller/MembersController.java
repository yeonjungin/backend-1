package whoami.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whoami.core.domain.members.Members;
import whoami.core.dto.members.LoginRequestDto;
import whoami.core.dto.members.LoginResponseDto;
import whoami.core.dto.members.MembersSaveRequestDto;
import whoami.core.dto.members.MembersUpdateRequestDto;
import whoami.core.security.JwtTokenProvider;
import whoami.core.service.MemberService;
import whoami.core.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MembersController {
    private final MemberService memberService;
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;

    // NOTE : 회원가입
    @PostMapping("/signup")
    public String joinMember(@RequestBody MembersSaveRequestDto requestDto) {
        if (memberService.joinMember(requestDto) != null) {
            return "회원가입 완료";
        }
        return "중복되는 아이디가 있습니다.";
    }
    // NOTE : 회원 정보 수정
    @PatchMapping("/users/update")
    public ResponseEntity<?> update(@RequestBody MembersUpdateRequestDto requestDto){
        return ResponseEntity.ok(memberService.update(requestDto));
    }

    // NOTE : 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request,
                                   HttpServletResponse response){
        LoginResponseDto loginResponseDto = memberService.loginUser(requestDto);
        if (loginResponseDto!=null){
            response.setHeader("accessToken", loginResponseDto.getAccessToken());
            response.setHeader("refreshToken",loginResponseDto.getRefreshToken());
            return ResponseEntity.ok(loginResponseDto);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    // FIXME : 로그아웃 로직 변경
    @GetMapping("/api/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        System.out.println("logout : " + request.getHeader("refreshToken"));
        redisService.delValues(request.getHeader("refreshToken"));
        return ResponseEntity.ok().body("로그아웃 성공!");
    }


    // NOTE : user test
    @PostMapping("/users/test")
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result","user ok");
        return result;
    }

    // NOTE : admin test
    @PostMapping("/admin/test")
    public Map adminResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result","admin ok");
        return result;
    }

    // NOTE : admin : 회원 전체 리스트 조회
    @GetMapping("/admin/members") // 회원 조회기능
    public String list(Model model){
        List<Members> members = memberService.listMembers(); // member를 다 가져올 수 있음
        model.addAttribute("members", members);
        return "members/memberList"; // FIXME : Return 페이지
    }


}


