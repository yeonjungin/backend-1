package whoami.core.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whoami.core.dto.follow.FollowRequestDto;
import whoami.core.dto.follow.UnfollowRequestDto;
import whoami.core.error.Helper;
import whoami.core.error.Response;
import whoami.core.service.FollowService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final Response response;
    private final FollowService followService;

    // NOTE : 팔로우
    @PostMapping("/users/follow")
    public ResponseEntity<?> follow(@RequestBody FollowRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return followService.follow(requestDto);
    }

    // NOTE : 언팔로우
    @DeleteMapping("/users/unfollow")
    public ResponseEntity<?> unfollow(@RequestBody UnfollowRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return followService.unfollow(requestDto);
    }
}
