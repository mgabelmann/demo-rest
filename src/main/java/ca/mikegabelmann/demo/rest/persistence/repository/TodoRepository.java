package ca.mikegabelmann.demo.rest.persistence.repository;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author mgabe
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    /**
     * Find by user id.
     * @param userId
     * @return
     */
    List<Todo> findByUserId(long userId);

    /**
     *
     * @param userId
     * @param id
     * @return
     */
    Optional<Todo> findByUserIdAndId(long userId, long id);

}
