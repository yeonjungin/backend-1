package whoami.core.dto.answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whoami.core.domain.member.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerUpdateRequestDto {
    private Long answerId; // PK
    private String answerContents;
    private Member member;

    @Builder
    public AnswerUpdateRequestDto(Long answerId, String answerContents){
        this.answerId = answerId;
        this.answerContents = answerContents;
    }

    public void setMember(Member member) {this.member = member;}
    public void setAnswerId(Long answerId) {this.answerId = answerId;}
}
