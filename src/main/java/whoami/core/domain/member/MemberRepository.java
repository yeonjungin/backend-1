package whoami.core.domain.member;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======

import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> upstream/master
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
