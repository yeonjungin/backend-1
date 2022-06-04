package whoami.core.dto.likeAnswer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.answer.Answer;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
public class LikeAnswerUpdateRequestDto {
    private Long likeAnswerId;  // pk
    private Long answerId;
    private Member member;

    @Builder
    public LikeAnswerUpdateRequestDto(Long likeAnswerId, Long answerId, Member member){
        this.likeAnswerId = likeAnswerId;
        this.answerId = answerId;
        this.member = member;
    }
}
