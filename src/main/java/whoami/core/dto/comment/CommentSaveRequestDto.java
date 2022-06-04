package whoami.core.dto.comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {
    private Long commentId;
    private String contents;
    private Member member;
    private Long answerId;

    @Builder
    public CommentSaveRequestDto(String contents){
        this.contents = contents;
    }

    public void setMember(Member member){ this.member = member; }
    public void setAnswerId(Long answerId){ this.answerId = answerId; }

    public Comment toEntity(){
        return Comment.builder()
                .commentId(commentId)
                .commentContents(contents)
                .member(member)
                .answerId(answerId)
                .build();
    }
}
