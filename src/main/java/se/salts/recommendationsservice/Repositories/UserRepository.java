package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.salts.recommendationsservice.Entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

   /* @Query("SELECT g.id FROM Genre g JOIN Media m ON g.id = m.genre.id " +
            "WHERE m.user.id = :userId " +
            "GROUP BY g.id " +
            "ORDER BY COUNT(m.id) DESC")
    List<Long> findTopGenresForUser(@Param("userId") Long userId); */
}
