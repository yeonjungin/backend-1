package whoami.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import whoami.core.domain.follow.Follow;
import whoami.core.domain.follow.FollowRepository;
import whoami.core.domain.members.Members;
import whoami.core.domain.members.MembersRepository;
import whoami.core.dto.follow.FollowRequestDto;
import whoami.core.dto.follow.FollowResponseDto;
import whoami.core.dto.follow.UnfollowRequestDto;
import whoami.core.error.Response;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MembersRepository membersRepository;
    private final Response response;

    @Transactional
    public ResponseEntity<?> follow(FollowRequestDto requestDto) {

        Optional<Members> followerId= membersRepository.findByUserId(requestDto.getFollowerId()); // 팔로워 (구독 당하는 사용자)
        Optional<Members> followingId = (Optional<Members>) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 로그인한 유저의 정보를 가져옴

        try{
            Optional<Follow> follows = followRepository.findByFollowerIdAndFollowingId(followerId.get().getMemberId(), followingId.get().getMemberId());
            if (follows.isEmpty()){
                Follow follow = new Follow(followerId.get(),followingId.get());
                followRepository.save(follow);
                return response.success(new FollowResponseDto(follow.getFollowId(),follow.getFollowerId().getUserId(),follow.getFollowingId().getUserId()),"팔로우가 완료되었습니다.",HttpStatus.OK);
            }
            else{
                return response.fail("이미 팔로워한 계정입니다.", HttpStatus.CONFLICT);
            }

        }catch(Exception e){
            return response.fail(e.toString(),HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public ResponseEntity<?> unfollow(UnfollowRequestDto requestDto) {
        Optional<Members> followerId= membersRepository.findByUserId(requestDto.getFollowerId());
        Optional<Members> followingId = (Optional<Members>) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 로그인한 유저의 정보를 가져옴
        Optional<Follow> follows = followRepository.findByFollowerIdAndFollowingId(followerId.get().getMemberId(), followingId.get().getMemberId());

        try {
            if (follows.isEmpty()){
                return response.fail("팔로우가 되어있지 않습니다.", HttpStatus.BAD_REQUEST);
            }
            else{
                follows.ifPresent(selectFollow -> {
                    followRepository.delete(selectFollow);
                });
                return response.success(Collections.EMPTY_LIST, "언팔로우가 완료되었습니다.", HttpStatus.OK);
            }

        }catch (Exception e){
            return response.fail(e.toString(),HttpStatus.CONFLICT);
        }
    }
}
