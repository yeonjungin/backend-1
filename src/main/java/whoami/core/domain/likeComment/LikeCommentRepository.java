package whoami.core.domain.likeComment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import whoami.core.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<Integer> countByCommentId(Long commentId);

    Optional<LikeComment> findByMemberAndCommentId(Member member, Long commentId);

    @Query(value = "SELECT * FROM like_comment AS lc WHERE lc.member_id=?1 AND lc.comment_id=?2", nativeQuery = true)
    List<LikeComment> findByMemberIdAndCommentId(@Param("memberId") long memberId, @Param("commentId") long commentId);
}
