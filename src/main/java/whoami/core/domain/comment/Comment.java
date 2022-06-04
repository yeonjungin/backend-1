package whoami.core.domain.comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.BaseTimeEntity;
import whoami.core.domain.member.Member;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String commentContents;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "upper_comments_id")
    private Long upperCommentsId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Comment(Long commentId, String commentContents, Long answerId, Long upperCommentsId, Member member){
        this.commentId = commentId;
        this.commentContents = commentContents;
        this.answerId = answerId;
        this.upperCommentsId = upperCommentsId;
        this.member = member;
    }

    public void update(Long commentId, String commentContents){
        this.commentId = commentId;
        this.commentContents = commentContents;
    }
}
