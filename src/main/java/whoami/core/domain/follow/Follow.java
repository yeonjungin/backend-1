package whoami.core.domain.follow;


import lombok.*;
import whoami.core.domain.members.Members;

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
    private Members followerId;

    @JoinColumn(name="following_id") // 팔로잉 (구독 하는 사용자)
    @ManyToOne
    private Members followingId;

    @Builder
    public Follow(Members followerId, Members followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

}