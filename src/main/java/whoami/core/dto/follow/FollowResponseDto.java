package whoami.core.dto.follow;
<<<<<<< HEAD
=======

>>>>>>> upstream/master
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowResponseDto {
    private final Long followId;
    private final String followerId;
    private final String followingId;

    @Builder
    public FollowResponseDto(Long followId, String followerId, String followingId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followingId = followingId;
    }
}
