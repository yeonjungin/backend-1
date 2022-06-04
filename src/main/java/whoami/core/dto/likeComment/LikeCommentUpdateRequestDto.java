package whoami.core.dto.likeComment;
import lombok.Builder;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.member.Member;

public class LikeCommentUpdateRequestDto {
    private Long likeCommentId;
    private Long commentId;
    private Member member;

    @Builder
    public LikeCommentUpdateRequestDto(Long likeCommentId, Long commentId, Member member){
        this.likeCommentId = likeCommentId;
        this.commentId = commentId;
        this.member = member;
    }
}
