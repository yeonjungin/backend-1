package whoami.core.dto.answer;
import lombok.Getter;
import whoami.core.domain.answer.Answer;

@Getter
public class AnswerResponseDto {
    private Long answerId; // PK
    private String answerContents;

    public AnswerResponseDto(Answer entity){
        this.answerId = entity.getAnswerId();
        this.answerContents = entity.getAnswerContents();
    }

}
