package whoami.core.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whoami.core.domain.answer.Answer;
import whoami.core.domain.answer.AnswerRepository;
import whoami.core.domain.likeAnswer.LikeAnswer;
import whoami.core.domain.likeAnswer.LikeAnswerRepository;
import whoami.core.domain.member.Member;
import whoami.core.error.Response;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeAnswerService {
    private final LikeAnswerRepository likeAnswerRepository;
    private final AnswerRepository answerRepository;
    private final Response response;

    // 답글 좋아요 추가
    public boolean addLike(Member member, Long answerId){
        Answer answer = answerRepository.findById(answerId).orElseThrow();
        if (isNotAlreadyLike(member,answerId)){ // 중복 좋아요 방지
            likeAnswerRepository.save(new LikeAnswer(answerId, member));
            return true;
        }
        return false;
    }

    // 답글 좋아요 삭제
    public void cancelLike(Member member, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow();
        LikeAnswer likeAnswer = likeAnswerRepository.findByMemberAndAnswerId(member, answerId).orElseThrow();
        likeAnswerRepository.delete(likeAnswer);
    }

    // 좋아요 리스트
    // 1. 좋아요를 count할 대상 answer를 가져온다.
    // 2. 가져온 answer로 like테이블에 쿼리한 결과를 List에 담는다.
    // 3. 현재 로그인한 사용자가 이미 해당 answer에 좋아요를 눌렀는지 검사하고 결과를 List에 담아 반환한다.
    public List<String> count(Long answerId, Member loginMember) {
        Answer answer = answerRepository.findById(answerId).orElseThrow();
        Integer answerLikeCount = likeAnswerRepository.countByAnswerId(answerId).orElse(0);
        List<String> resultData = new ArrayList<>(Arrays.asList(String.valueOf(answerLikeCount)));
        if (Objects.nonNull(loginMember)) {
            resultData.add(String.valueOf(isNotAlreadyLike(loginMember, answerId)));
            return resultData;
        }
        return resultData;
    }

    // 사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Member member, Long answerId){
        return likeAnswerRepository.findByMemberIdAndAnswerId(member.getMemberId(), answerId).isEmpty();
    }
}
