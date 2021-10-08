package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test TodoController by mocking Service.
 *
 * @author mgabe
 */
@WebMvcTest(TodoController.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private Todo todo1;


    @BeforeEach
    void beforeEach() {
        this.todo1 = new Todo(1L, 1L, "task", LocalDateTime.now(), null, 1L);
    }

    @Test
    @DisplayName("findByUserId - with result")
    void test1_findByUser() throws Exception {
        List<Todo> records = Arrays.asList(todo1);
        /* NOTE: running the following line in IntelliJ with coverage fails. Why?
                 List<Todo> records = List.of(todo1);
         */
        Mockito.when(todoService.findByUserId(1L)).thenReturn(records);

        mvc.perform(get(TodoController.PATH_USER_USERID_TODO, 1))
                .andExpectAll(
                        status().isOk(),
                        content().string(startsWith("[{\"id\":1,"))
                );
    }

    @Test
    @DisplayName("findByUserId - without result")
    void test2_findByUser() throws Exception {
        mvc.perform(get(TodoController.PATH_USER_USERID_TODO, 1))
                .andExpectAll(
                        status().isOk(),
                        content().string(startsWith("[]"))
                );
    }



    /* NOTE: refactor these tests since they don't use MVC.

    @Test
    @DisplayName("findByUserAndId - with result")
    void test1_findByUserAndId() {
        List<Todo> records = Arrays.asList(todo1);
        Mockito.when(todoService.findByUserIdAndId(1L, 1L)).thenReturn(Optional.of(records.get(0)));

        ResponseEntity<Todo> result = controller.findByUserAndId(1L, 1L);

        this.validateOK(result);
    }

    @Test
    @DisplayName("findByUserAndId - without result")
    void test2_findByUserAndId() {
        ResponseStatusException rse = Assertions.assertThrows(ResponseStatusException.class, () -> controller.findByUserAndId(1L, 1L));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, rse.getStatus());
    }

    @Test
    @DisplayName("create - with result")
    void create() {
        Mockito.when(todoService.createOrUpdate(todo1)).thenReturn(todo1);

        ResponseEntity<Todo> result = controller.create(1L, todo1);

        this.validateOK(result);
    }

    @Test
    void update() {
        Mockito.when(todoService.createOrUpdate(todo1)).thenReturn(todo1);

        ResponseEntity<Todo> result = controller.update(1L, 1L, todo1);

        this.validateOK(result);
    }

    @Test
    void delete() {
        controller.delete(1L, 1L);
    }

    private void validateOK(final ResponseEntity<?> result) {
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
    }
    */
}