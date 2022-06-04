package whoami.core.dto.comment;
import lombok.Getter;
import whoami.core.domain.comment.Comment;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String contents;

    public CommentResponseDto(Comment entity){
        this.commentId = entity.getCommentId();
        this.contents = entity.getCommentContents();
    }
}
