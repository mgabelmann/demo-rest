package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author mgabe
 */
@RestController
public class TodoController {
    public static final String PATH_FIND_BY_USER = "/user/{userId}/todo";

    /** Service for Todo records. */
    private final TodoService todoService;


    /**
     * Autowired constructor.
     * @param todoService todo service
     */
    @Autowired
    public TodoController(final TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Find todo records by user id.
     * @param userId user id
     * @return records
     */
    @GetMapping(path = TodoController.PATH_FIND_BY_USER)
    public ResponseEntity<List<Todo>> findByUser(
            @PathVariable("userId") long userId) {

        List<Todo> records = todoService.findByUserId(userId);

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    /**
     * Find todo record by user id and id.
     * @param userId user id
     * @param id id
     * @return record
     */
    @GetMapping(path="/user/{userId}/todo/{id}")
    public ResponseEntity<Todo> findByUserAndId(
            @PathVariable("userId") long userId,
            @PathVariable("id") long id) {

        Optional<Todo> todo = todoService.findByUserIdAndId(userId, id);

        if (todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a record.
     * @param userId user id
     * @param todo record
     * @return record
     */
    @PostMapping(path="/user/{userId}/todo")
    public ResponseEntity<Todo> create(
            @PathVariable("userId") long userId,
            @RequestBody Todo todo) {

        Todo created = todoService.createOrUpdate(todo);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    /**
     * Update Todo record.
     * @param userId user id
     * @param id id
     * @param todo record
     * @return record
     */
    @PutMapping(path="/user/{userId}/todo/{id}")
    public ResponseEntity<Todo> update(
            @PathVariable("userId") long userId,
            @PathVariable("id") long id,
            @RequestBody Todo todo) {

        /* copy values
        return todoService.findByUserIdAndId(userId, id)
                .map(tmp -> {
                    tmp.setTask(todo.getTask());
                    tmp.setDateCreated(todo.getDateCreated());
                    tmp.setDateCompleted(todo.getDateCompleted());
                    return new ResponseEntity<Todo>(todoService.createOrUpdate(todo), HttpStatus.OK);

                }).orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        */

        Todo updated = todoService.createOrUpdate(todo);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Delete Todo record.
     * @param userId user id
     * @param id id
     */
    @DeleteMapping("/user/{userId}/todo/{id}")
    public void delete(
            @PathVariable("userId") long userId,
            @PathVariable("id") long id) {

        todoService.delete(userId, id);
    }

}
