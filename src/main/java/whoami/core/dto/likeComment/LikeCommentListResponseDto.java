package whoami.core.dto.likeComment;
import lombok.Getter;
import whoami.core.domain.likeComment.LikeComment;
import whoami.core.domain.member.Member;

@Getter
public class LikeCommentListResponseDto {
    private Long likeCommentId;
    private Long commentId;
    private Member member;

    public LikeCommentListResponseDto(LikeComment entity){
        this.likeCommentId = entity.getId();
        this.commentId = entity.getCommentId();
        this.member = entity.getMember();
    }
}
