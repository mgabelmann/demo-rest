package ca.mikegabelmann.demo.rest.persistence.repository;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Search for Todo records.
 * @author mgabe
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * Find records by user id.
     * @param userId user id
     * @return records
     */
    List<Todo> findByUserId(long userId);

    /**
     * Find record by userId and record id.
     * @param userId user id
     * @param id record id
     * @return record
     */
    Optional<Todo> findByUserIdAndId(long userId, long id);

}
