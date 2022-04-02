package whoami.core.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members,Long> {
    Optional<Members> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
