package whoami.core.dto.likeAnswer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.likeAnswer.LikeAnswer;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
public class LikeAnswerSaveRequestDto {
    private Long likeAnswerId;  // pk
    private Long answerId;
    private Member member;

    @Builder
    public LikeAnswerSaveRequestDto(Long likeAnswerId, Long answerId, Member member){
        this.likeAnswerId = likeAnswerId;
        this.answerId = answerId;
        this.member = member;
    }

    public LikeAnswer toEntity(){
        return LikeAnswer.builder()
                .id(likeAnswerId)
                .answerId(answerId)
                .member(member)
                .build();
    }
}
