package whoami.core.domain.likeAnswer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.member.Member;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="like_answer")
public class LikeAnswer {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 좋아요 된 answer
    @Column(name = "answer_id")
    private Long answerId;

    // 좋아요 한 사람
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public LikeAnswer(Long id, Long answerId, Member member) {
        this.id = id;
        this.answerId = answerId;
        this.member = member;
    }

    public LikeAnswer(Long answerId, Member member) {
        this.answerId = answerId;
        this.member = member;
    }
}
