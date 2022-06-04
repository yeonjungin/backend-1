package whoami.core.dto.comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long commentId;
    private String contents;
    private Member member;

    @Builder
    public CommentUpdateRequestDto(Long commentId, String contents){
        this.commentId = commentId;
        this.contents = contents;
    }

    public void setMember(Member member){this.member = member;}
    public void setCommentId(Long commentId){this.commentId = commentId;}
}
