package whoami.core.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whoami.core.domain.comment.Comment;
import whoami.core.domain.likeComment.LikeComment;
import whoami.core.domain.member.Member;
import whoami.core.domain.comment.CommentRepository;
import whoami.core.domain.likeComment.LikeCommentRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeCommentService {
    private final LikeCommentRepository likeCommentRepository;
    private final CommentRepository commentRepository;

    // 댓글 좋아요 추가
    public boolean addLike(Member member, Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (isNotAlreadyLike(member, commentId)){ // 중복 좋아요 방지
            likeCommentRepository.save(new LikeComment(commentId, member));
            return true;
        }
        return false;
    }

    // 댓글 좋아요 삭제
    public void cancelLike(Member member, Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        LikeComment likeComment = likeCommentRepository.findByMemberAndCommentId(member, commentId).orElseThrow();
        likeCommentRepository.delete(likeComment);
    }

    // 좋아요 리스트
    public List<String> count(Long commentId, Member loginMember){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Integer commentLikeCount = likeCommentRepository.countByCommentId(commentId).orElse(0);
        List<String> resultData = new ArrayList<>(Arrays.asList(String.valueOf(commentLikeCount)));
        if (Objects.nonNull(loginMember)){
            resultData.add(String.valueOf(isNotAlreadyLike(loginMember, commentId)));
            return resultData;
        }
        return resultData;
    }

    // 사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Member member, Long commentId){
        return likeCommentRepository.findByMemberIdAndCommentId(member.getMemberId(), commentId).isEmpty();
    }
}
