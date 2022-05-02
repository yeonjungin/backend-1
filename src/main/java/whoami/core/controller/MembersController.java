package whoami.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import whoami.core.service.AwsS3Service;
import whoami.core.domain.members.Members;
import whoami.core.dto.members.*;
import whoami.core.error.Helper;
import whoami.core.error.Response;
import whoami.core.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MembersController {
    private final MemberService memberService;
    private final Response response;
    private final AwsS3Service s3Uploader;

    // NOTE : 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> joinMember(@RequestBody MembersSaveRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.joinMember(requestDto);
    }

    // NOTE : 회원 정보 수정
    @PatchMapping("/users/update")
    public ResponseEntity<?> updateMember(@RequestBody MembersUpdateRequestDto requestDto,Errors errors){
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        else{
            return memberService.updateMember(requestDto);
        }
    }


    // FIXME : 회원 탈퇴 (회원과 관련된 게시글, 댓글 모두 삭제해야함)
    @DeleteMapping("/users/delete")
    public ResponseEntity<?> delete(@RequestBody MembersDeleteRequestDto requestDto, Errors errors){
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        else{
            return memberService.deleteMember(requestDto);
        }
    }

    // NOTE : 회원가입 프로필 추가
    @PostMapping(value="/users/profile",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> profileUpload(@RequestPart ProfileUploadRequestDto requestDto, @RequestPart MultipartFile multipartFile, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }else {
            return s3Uploader.profileUpload(requestDto,multipartFile);
        }
    }

    // NOTE : 회원가입 프로필 삭제
    @PatchMapping("/users/profileDelete")
    public ResponseEntity<?> profileDelete(@RequestBody ProfileDeleteRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }else {
            return s3Uploader.profileDelete(requestDto);
        }
    }

    // NOTE : 로그인 (토큰 발급)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.loginUser(requestDto);
    }

    // NOTE : 토큰 재발급
    @PatchMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody ReissueTokenRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.reissue(requestDto);
    }

    // NOTE : 로그아웃
    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequestDto requestDto,Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.logout(requestDto);
    }

    // NOTE : admin : 회원 전체 리스트 조회
    @GetMapping("/admin/users") // 회원 조회기능
    public String list(Model model){
        List<Members> members = memberService.listMembers(); // member를 다 가져올 수 있음
        model.addAttribute("members", members);
        return "members/memberList"; // FIXME : Return 페이지
    }


}


