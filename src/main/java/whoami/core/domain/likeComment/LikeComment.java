package whoami.core.domain.likeComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.member.Member;
import whoami.core.domain.comment.Comment;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="like_comment")
public class LikeComment {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 좋아요 된 comment
    @Column(name = "comment_id")
    private Long commentId;

    // 좋아요 한 사람
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public LikeComment(Long id, Long commentId, Member member){
        this.id = id;
        this.commentId = commentId;
        this.member = member;
    }

    public LikeComment(Long commentId, Member member){
        this.commentId = commentId;
        this.member = member;
    }
}
