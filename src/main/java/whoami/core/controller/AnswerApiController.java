package whoami.core.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import whoami.core.domain.member.Member;
import whoami.core.dto.answer.AnswerListResponseDto;
import whoami.core.dto.answer.AnswerResponseDto;
import whoami.core.dto.answer.AnswerSaveRequestDto;
import whoami.core.dto.answer.AnswerUpdateRequestDto;
import whoami.core.error.Helper;
import whoami.core.error.Response;
import whoami.core.service.AnswerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Controller
public class AnswerApiController {
    private final AnswerService answerService;
    private final Response response;

    // 1. 조회
    // 로그인한 user의 모든 답글 조회
    @RequestMapping(value = "/answer/list", method = RequestMethod.GET)
    public List<AnswerListResponseDto> findByMemberId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 로그인한 유저의 정보를 가져옴
        Optional<Member> userDetails = (Optional<Member>)principal;
        Long memberId = userDetails.get().getMemberId();
        return answerService.findByMemberId(memberId);
    }
    
    // 해당 답글 조회
    @RequestMapping(value = "/answer/{answerId}", method = RequestMethod.GET)
    public AnswerResponseDto findById(@PathVariable Long answerId){
        return answerService.findById(answerId);
    }

    // 2. 작성
    // 답글 내용 작성
    @RequestMapping(value="/answer", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody AnswerSaveRequestDto requestDTO, Errors errors){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        requestDTO.setMember(userDetails.get());
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return answerService.save(requestDTO);
    }

    // 답글 내용 수정
    @RequestMapping(value="/answer/{answerId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable Long answerId, @RequestBody AnswerUpdateRequestDto requestDTO, Errors errors){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        requestDTO.setMember(userDetails.get());
        requestDTO.setAnswerId(answerId);
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return answerService.update(answerId, requestDTO);
    }

    // 답글 내용 삭제
    @DeleteMapping("/answer/{answerId}")
    public ResponseEntity<?> delete(@PathVariable Long answerId){
        return answerService.delete(answerId);
    }
}
