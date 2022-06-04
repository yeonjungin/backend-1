package whoami.core.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import whoami.core.domain.member.Member;
import whoami.core.service.LikeAnswerService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeAnswerApiController {
    private final LikeAnswerService likeAnswerService;
    // 좋아요 취소
    @DeleteMapping("/likeAnswer/{answerId}")
    public ResponseEntity<String> cancelLike(@PathVariable Long answerId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        Member loginMember = userDetails.get();
        if (loginMember != null) {
            likeAnswerService.cancelLike(loginMember, answerId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 좋아요 등록
    @PostMapping("/likeAnswer/{answerId}")
    public ResponseEntity<String> addLike(@PathVariable Long answerId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> userDetails = (Optional<Member>)principal;
        Member loginMember = userDetails.get();
        boolean result = false;
        if (Objects.nonNull(principal)){
            result = likeAnswerService.addLike(loginMember, answerId);
        }
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
