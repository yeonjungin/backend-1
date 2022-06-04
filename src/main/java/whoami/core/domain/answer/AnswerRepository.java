package whoami.core.domain.answer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    List<Answer> findAll();

    @Query(value = "SELECT * FROM answer As a WHERE a.member_id =?1", nativeQuery = true)
    List<Answer> findByMemberId(@Param("memberId") long memberId);
}