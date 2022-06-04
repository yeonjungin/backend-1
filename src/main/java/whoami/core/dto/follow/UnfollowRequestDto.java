package whoami.core.dto.follow;
<<<<<<< HEAD
=======

>>>>>>> upstream/master
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnfollowRequestDto {
    private String followerId;
<<<<<<< HEAD
    private String followingId;

    @Builder
    public UnfollowRequestDto(String followerId,String followingId) {
        this.followerId=followerId;
        this.followingId=followingId;
=======

    @Builder
    public UnfollowRequestDto(String followerId) {
        this.followerId=followerId;
>>>>>>> upstream/master
    }
}
