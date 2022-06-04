package whoami.core.domain.follow;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import io.lettuce.core.dynamic.annotation.Param;

>>>>>>> upstream/master
import java.util.Optional;

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    @Query(value = "SELECT * FROM follow WHERE follower_id = :followerId AND following_id=:followingId", nativeQuery = true)
<<<<<<< HEAD
    Optional <Follow> findByFollowerIdAndFollowingId(long followerId, long followingId);
=======
    Optional <Follow> findByFollowerIdAndFollowingId(@Param("follower_id") long followerId, @Param("following_id")long followingId);
>>>>>>> upstream/master
}
