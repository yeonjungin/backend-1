package whoami.core.dto.answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.answer.Answer;
import whoami.core.domain.member.Member;

@Getter
@NoArgsConstructor
//@JsonAutoDetect
public class AnswerSaveRequestDto {
    private Long answerId; // PK
    private String answerContents;
    private Member member;

    @Builder
    public AnswerSaveRequestDto(String answerContents) {
        this.answerContents = answerContents;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Answer toEntiy() {
        return Answer.builder()
                .answerId(answerId)
                .answerContents(answerContents)
                .member(member)
                .build();
    }
}
