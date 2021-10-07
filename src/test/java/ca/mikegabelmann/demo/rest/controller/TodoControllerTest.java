package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test TodoController by mocking Service.
 * @author mgabe
 */
@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController controller;

    private List<Todo> records;


    @BeforeEach
    void setUp() {
        this.records = Arrays.asList(new Todo(1L, 1L, "", LocalDateTime.now(), null, 1L));
    }

    @Test
    @DisplayName("findByUserId - with result")
    void test1_findByUser() {
        Mockito.when(todoService.findByUserId(1L)).thenReturn(records);

        ResponseEntity<List<Todo>> results = controller.findByUser(1L);

        Assertions.assertNotNull(results);
        Assertions.assertEquals(HttpStatus.OK, results.getStatusCode());
        Assertions.assertEquals(1, results.getBody().size());
        Assertions.assertEquals(records.get(0), results.getBody().get(0));
    }

    @Test
    @DisplayName("findByUserId - without result")
    void test2_findByUser() {
        ResponseEntity<List<Todo>> results = controller.findByUser(1L);

        Assertions.assertNotNull(results);
        Assertions.assertEquals(HttpStatus.OK, results.getStatusCode());
        Assertions.assertEquals(0, results.getBody().size());
    }


    @Test
    @DisplayName("findByUserAndId - with result")
    void findByUserAndId() {
        Mockito.when(todoService.findByUserIdAndId(1L, 1L)).thenReturn(Optional.of(records.get(0)));

        ResponseEntity<Todo> result = controller.findByUserAndId(1L, 1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void create() {
        Assertions.fail("TODO");
    }

    @Test
    void update() {
        Assertions.fail("TODO");
    }

    @Test
    void delete() {
        Assertions.fail("TODO");
    }

}