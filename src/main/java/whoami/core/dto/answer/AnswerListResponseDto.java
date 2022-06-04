package whoami.core.dto.answer;
import lombok.Getter;
import whoami.core.domain.answer.Answer;

@Getter
public class AnswerListResponseDto {
    private Long answerId; // PK
    private String answerContents;

    public AnswerListResponseDto(Answer entity){
        this.answerId = entity.getAnswerId();
        this.answerContents = entity.getAnswerContents();
    }

}
