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
import whoami.core.domain.member.Member;
import whoami.core.dto.member.*;
import whoami.core.error.Helper;
import whoami.core.error.Response;
import whoami.core.service.MemberService;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final Response response;
    private final AwsS3Service s3Uploader;

    // NOTE : 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> joinMember(@RequestBody MemberSaveRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.joinMember(requestDto);
    }

    // NOTE : 회원 정보 수정
    @PatchMapping("/users/update")
    public ResponseEntity<?> updateMember(@RequestBody MemberUpdateRequestDto requestDto, Errors errors){
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        else{
            return memberService.updateMember(requestDto);
        }
    }

    // NOTE : 회원 탈퇴
    @DeleteMapping("/users/delete")
    public ResponseEntity<?> delete() {
        try{
            return memberService.deleteMember();
        }catch (Exception e){
            System.out.println(e);
            return null;
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
    public ResponseEntity<?> profileDelete() {
        try{
            return s3Uploader.profileDelete();
        }catch (Exception e){
            System.out.println(e);
            return null;
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

}


