package whoami.core.dto.follow;
<<<<<<< HEAD
import lombok.*;
import lombok.Builder;
=======

import lombok.*;
import lombok.Builder;
import whoami.core.domain.follow.Follow;
>>>>>>> upstream/master

@Getter
@Setter
@NoArgsConstructor
public class FollowRequestDto {
    private String followerId;
<<<<<<< HEAD
    private String followingId;

    @Builder
    public FollowRequestDto(String followerId,String followingId) {
        this.followerId=followerId;
        this.followingId=followingId;
    }


=======

    @Builder
    public FollowRequestDto(String followerId) {
        this.followerId=followerId;
    }

>>>>>>> upstream/master
}
