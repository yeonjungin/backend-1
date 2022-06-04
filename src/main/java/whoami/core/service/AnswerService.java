package whoami.core.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import whoami.core.dto.answer.AnswerListResponseDto;
import whoami.core.dto.answer.AnswerResponseDto;
import whoami.core.dto.answer.AnswerSaveRequestDto;
import whoami.core.dto.answer.AnswerUpdateRequestDto;
import whoami.core.domain.answer.Answer;
import whoami.core.domain.answer.AnswerRepository;
import whoami.core.error.Response;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final Response response;

    // 답글(Answer) 조회
    // 1 - 해당 user의 모든 답글 조회
    public List<AnswerListResponseDto> findByMemberId(Long memberId){
        return answerRepository.findByMemberId(memberId).stream()
                .map(AnswerListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 2 - 해당 답글 조회
    public AnswerResponseDto findById(Long answerId){
        Answer entity = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 답글이 존재하지 않습니다. id="+answerId));
        return new AnswerResponseDto(entity);
    }

    // 답글(Answer) 작성
    @Transactional
    public ResponseEntity<? extends Object> save(AnswerSaveRequestDto requestDTD){
        try {
            answerRepository.save(requestDTD.toEntiy()).getAnswerId();
            return response.success("답글 작성이 완료되었습니다.");
        }catch (Exception e){
            return  response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    // 답글(Answer) 수정
    @Transactional
    public ResponseEntity<? extends Object> update (Long id, AnswerUpdateRequestDto requestDTO){
        try{
            Answer answer = answerRepository.findById(id).orElseThrow(()
                    -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
            answer.update(requestDTO.getAnswerId(), requestDTO.getAnswerContents());
            return response.success("답글 수정이 완료되었습니다.");
        }catch (Exception e){
            return response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    // 답글(Answer) 삭제
    @Transactional
    public ResponseEntity<? extends Object> delete(Long id){
        try {
            Answer answer = answerRepository.findById(id).orElseThrow(()
                    -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
            answerRepository.delete(answer);
            return response.success("답글 삭제가 완료되었습니다. : "+id);
        }catch (Exception e){
            return response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
