package whoami.core.domain.answer;
import lombok.*;
import whoami.core.domain.BaseTimeEntity;
import whoami.core.domain.member.Member;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="answer")
public class Answer extends BaseTimeEntity {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(name = "contents", length = 1000, nullable = false)
    private String answerContents;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Answer(Long answerId, String answerContents, Member member) {
        this.answerId = answerId;
        this.answerContents = answerContents;
        this.member = member;
    }

    public void update(Long answerId, String answerContents){
        this.answerId = answerId;
        this.answerContents = answerContents;
    }

}
