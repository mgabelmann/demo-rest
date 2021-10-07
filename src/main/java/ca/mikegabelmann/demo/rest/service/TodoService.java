package ca.mikegabelmann.demo.rest.service;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;

import java.util.List;
import java.util.Optional;


/**
 * Todo service.
 * @author mgabe
 */
public interface TodoService {
    /**
     * Find Todo records by user id.
     * @param userId user id
     * @return records
     */
    List<Todo> findByUserId(long userId);

    /**
     * Find Todo record by user id and id.
     * @param userId user id
     * @param id record id
     * @return 0 or 1 record
     */
    Optional<Todo> findByUserIdAndId(long userId, long id);

    /**
     * Delete a record.
     * @param userId user id
     * @param id record id
     */
    void delete(long userId, long id);

    /**
     * Create or update a record.
     * @param todo record
     * @return record
     */
    Todo createOrUpdate(Todo todo);
}
