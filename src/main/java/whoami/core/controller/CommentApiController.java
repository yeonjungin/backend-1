package whoami.core.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import whoami.core.domain.answer.Answer;
import whoami.core.domain.answer.AnswerRepository;
import whoami.core.domain.member.Member;
import whoami.core.dto.comment.CommentListResponseDto;
import whoami.core.dto.comment.CommentResponseDto;
import whoami.core.dto.comment.CommentSaveRequestDto;
import whoami.core.dto.comment.CommentUpdateRequestDto;
import whoami.core.error.Helper;
import whoami.core.error.Response;
import whoami.core.service.CommentService;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;
    private final AnswerRepository answerRepository;
    private final Response response;

    // 1. 조회
    // 현재 answer의 모든 comment 조회
    @RequestMapping(value="/answer/{answerId}/comment/list", method=RequestMethod.GET)
    public List<CommentListResponseDto> findByAnswerId(@PathVariable Long answerId){
        return commentService.findByAnswerId(answerId);
    }
    
    // 해당 comment 조회
    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
    public CommentResponseDto findById(@PathVariable Long commentId){
        return commentService.findById(commentId);
    }

    // 댓글 내용 작성
    @RequestMapping(value = "/answer/{answerId}/comment", method = RequestMethod.POST)
    public ResponseEntity<?> save(@PathVariable Long answerId, @RequestBody CommentSaveRequestDto requestDto, Errors errors){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        requestDto.setMember(userDetails.get());
        requestDto.setAnswerId(answerId);
        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return commentService.save(requestDto);
    }
    // 댓글 내용 수정
    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto, Errors errors){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>) principal;
        requestDto.setMember(userDetails.get());
        requestDto.setCommentId(commentId);
        if (errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return commentService.update(commentId, requestDto);
    }

    // 댓글 내용 삭제
    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long commentId){
        return commentService.delete(commentId);
    }
}
