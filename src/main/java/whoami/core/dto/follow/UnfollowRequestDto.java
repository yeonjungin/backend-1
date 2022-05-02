package whoami.core.dto.follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnfollowRequestDto {
    private String followerId;
    private String followingId;

    @Builder
    public UnfollowRequestDto(String followerId,String followingId) {
        this.followerId=followerId;
        this.followingId=followingId;
    }
}
