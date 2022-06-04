package whoami.core.domain.follow;
import lombok.*;
import whoami.core.domain.member.Member;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id")
    private Long followId;

    @JoinColumn(name="follower_id") // 팔로워 (구독 당하는 사용자)
    @ManyToOne
    private Member followerId;

    @JoinColumn(name="following_id") // 팔로잉 (구독하는 사용자)
    @ManyToOne
    private Member followingId;

    @Builder
    public Follow(Member followerId, Member followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

}
