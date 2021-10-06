package ca.mikegabelmann.demo.rest.service;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoService {
    List<Todo> findByUserId(long userId);

    Optional<Todo> findByUserIdAndId(long userId, long id);

    void delete(long userId, long id);

    Todo createOrUpdate(Todo todo);
}
