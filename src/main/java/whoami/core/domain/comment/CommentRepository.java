package whoami.core.domain.comment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

    @Query(value = "SELECT * FROM comment AS c WHERE c.answer_id=?1", nativeQuery = true)
    List<Comment> findByAnswerId(@Param("answerId") Long answerId);
}
