package whoami.core.dto.likeComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.likeComment.LikeComment;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
public class LikeCommentSaveRequestDto {
    private Long likeCommentId;
    private Long commentId;
    private Member member;

    @Builder
    public LikeCommentSaveRequestDto(Long likeCommentId, Long commentId, Member member){
        this.likeCommentId = likeCommentId;
        this.commentId = commentId;
        this.member = member;
    }

    public LikeComment toEntity(){
        return LikeComment.builder()
                .id(likeCommentId)
                .commentId(commentId)
                .member(member)
                .build();
    }
}
