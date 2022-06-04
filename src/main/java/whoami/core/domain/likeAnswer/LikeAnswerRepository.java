package whoami.core.domain.likeAnswer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import whoami.core.domain.member.Member;
import java.util.List;
import java.util.Optional;

public interface LikeAnswerRepository extends JpaRepository<LikeAnswer, Long> {
    Optional<Integer> countByAnswerId(Long answerId);

    Optional<LikeAnswer> findByMemberAndAnswerId(Member member, Long answerId);

    @Query(value = "SELECT * FROM like_answer As la WHERE la.member_id=?1 AND la.answer_id=?2", nativeQuery = true)
    List<LikeAnswer> findByMemberIdAndAnswerId(@Param("memberId") long memberId, @Param("answerId") long answerId);
}