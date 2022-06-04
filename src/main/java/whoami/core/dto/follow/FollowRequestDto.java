package whoami.core.dto.follow;
import lombok.*;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
public class FollowRequestDto {
    private String followerId;

    @Builder
    public FollowRequestDto(String followerId) {
        this.followerId=followerId;
    }
}
