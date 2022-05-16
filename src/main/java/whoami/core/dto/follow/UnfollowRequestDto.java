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

    @Builder
    public UnfollowRequestDto(String followerId) {
        this.followerId=followerId;
    }
}
