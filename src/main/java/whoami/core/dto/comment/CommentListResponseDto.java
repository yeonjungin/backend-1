package whoami.core.dto.comment;
import lombok.Getter;
import whoami.core.domain.comment.Comment;

@Getter
public class CommentListResponseDto {
    private Long commentId;
    private String contents;

    public CommentListResponseDto(Comment entity){
        this.commentId = entity.getCommentId();
        this.contents = entity.getCommentContents();
    }
}
