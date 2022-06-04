package whoami.core.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import whoami.core.domain.member.Member;
import whoami.core.service.LikeCommentService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeCommentApiController {
    private final LikeCommentService likeCommentService;

    // 좋아요 count
    @GetMapping("/likeComment/{commentId}")
    public ResponseEntity<List<String>> getLikeCount(@PathVariable Long commentId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        Member loginMember = userDetails.get();
        List<String> resultData = likeCommentService.count(commentId, loginMember);
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    // 좋아요 취소
    @DeleteMapping("/likeComment/{commentId}")
    public ResponseEntity<String> cancelLike(@PathVariable Long commentId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        Member loginMember = userDetails.get();
        if (loginMember != null){
            likeCommentService.cancelLike(loginMember, commentId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 좋아요 등록
    @PostMapping("/likeComment/{commentId}")
    public ResponseEntity<String> addLike(@PathVariable Long commentId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        Member loginMember = userDetails.get();
        boolean result = false;
        if (Objects.nonNull(principal)){
            result = likeCommentService.addLike(loginMember, commentId);
        }
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
