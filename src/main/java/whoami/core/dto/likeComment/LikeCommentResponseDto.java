package whoami.core.dto.likeComment;
import lombok.Getter;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.likeComment.LikeComment;
import whoami.core.domain.member.Member;

@Getter
public class LikeCommentResponseDto {
    private Long likeCommentId;
    private Long commentId;
    private Member member;

    public LikeCommentResponseDto(LikeComment entity){
        this.likeCommentId = entity.getId();
        this.commentId = entity.getCommentId();
        this.member = entity.getMember();
    }
}
