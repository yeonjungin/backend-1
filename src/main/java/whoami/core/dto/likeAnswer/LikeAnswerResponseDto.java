package whoami.core.dto.likeAnswer;
import lombok.Getter;
import whoami.core.domain.likeAnswer.LikeAnswer;
import whoami.core.domain.member.Member;

@Getter
public class LikeAnswerResponseDto {
    private Long likeAnswerId;  // pk
    private Long answerId;
    private Member member;

    public LikeAnswerResponseDto(LikeAnswer entity){
        this.likeAnswerId = entity.getId();
        this.answerId = entity.getAnswerId();
        this.member = entity.getMember();
    }
}
