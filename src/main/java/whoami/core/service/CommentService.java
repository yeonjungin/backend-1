package whoami.core.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import whoami.core.dto.comment.CommentListResponseDto;
import whoami.core.dto.comment.CommentResponseDto;
import whoami.core.dto.comment.CommentSaveRequestDto;
import whoami.core.dto.comment.CommentUpdateRequestDto;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.comment.CommentRepository;
import whoami.core.error.Response;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final Response response;
    
    // 댓글(Comment) 조회
    // 1 - 해당 Answer의 모든 댓글 조회
    public List<CommentListResponseDto> findByAnswerId(Long answerId){
        return commentRepository.findByAnswerId(answerId).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 2 - 해당 댓글 조회
    public CommentResponseDto findById(Long commentId){
        Comment entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return new CommentResponseDto(entity);
    }
    
    // 댓글(Comment) 작성
    @Transactional
    public ResponseEntity<? extends Object> save(CommentSaveRequestDto requestDto){
        try{
            commentRepository.save(requestDto.toEntity()).getCommentId();
            return response.success("댓글 작성이 완료되었습니다.");
        }catch (Exception e){
            return response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
    
    // 댓글(Comment) 수정
    @Transactional
    public ResponseEntity<? extends Object> update (Long id, CommentUpdateRequestDto requestDto){
        try{
            Comment comment = commentRepository.findById(id).orElseThrow(()
                    -> new IllegalArgumentException("해당 댓글이 없습니다. id = " + id));
            comment.update(requestDto.getCommentId(), requestDto.getContents());
            return response.success("댓글 수정이 완료되었습니다.");
        }catch (Exception e){
            return response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
    
    // 댓글(Comment) 삭제
    @Transactional
    public ResponseEntity<? extends Object> delete(Long id){
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 댓글이 없습니다. id = " + id));
            commentRepository.delete(comment);
            return response.success("댓글 삭제가 완료되었습니다. id = "+id);
        }catch (Exception e){
            return response.fail(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
