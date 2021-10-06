package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    private TodoService todoService;


    /**
     * Autowired constructor.
     * @param todoService todo service
     */
    @Autowired
    public TodoController(final TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path="/user/{userId}/todo")
    public ResponseEntity<List<Todo>> findByUser(
            @PathVariable("userId") long userId) {

        List<Todo> records = todoService.findByUserId(userId);

        return new ResponseEntity<List<Todo>>(records, HttpStatus.OK);
    }

    @GetMapping(path="/user/{userId}/todo/{id}")
    public ResponseEntity<Todo> findByUserAndId(
            @PathVariable("userId") long userId,
            @PathVariable("id") long id) {

        Optional<Todo> todo = todoService.findByUserIdAndId(userId, id);

        if (todo.isPresent()) {
            return new ResponseEntity<Todo>(todo.get(), HttpStatus.OK);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //TODO: @PostMapping

    @PutMapping(path="/user/{userId}/todo")
    public ResponseEntity<Todo> createOrUpdate(
            @PathVariable("userId") long userId,
            final Todo todo) {

        return new ResponseEntity<Todo>(todoService.createOrUpdate(todo), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/todo/{id}")
    public void delete(
            @PathVariable("userId") long userId,
            @PathVariable("id") long id,
            final HttpServletRequest request) {

        todoService.delete(userId, id);
    }

}
