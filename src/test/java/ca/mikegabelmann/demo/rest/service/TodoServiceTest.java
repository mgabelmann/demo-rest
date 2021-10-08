package ca.mikegabelmann.demo.rest.service;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.persistence.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Todo service test.
 * @author mgabe
 */
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    private Todo todo1;


    @BeforeEach
    void beforeEach() {
        this.todo1 = new Todo(1L, 1L, "task", LocalDateTime.now(), null, 1L);
    }

    @Test
    @DisplayName("findByUserId - with result")
    void test1_findByUserId() {
        Mockito.when(todoRepository.findByUserId(1L)).thenReturn(Arrays.asList(todo1));

        List<Todo> result = todoService.findByUserId(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("findByUserId - without result")
    void test2_findByUserId() {
        List<Todo> result = todoService.findByUserId(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    @DisplayName("findByUserIdAndId - with result")
    void test1_findByUserIdAndId() {
        Mockito.when(todoRepository.findByUserIdAndId(1L, 1L)).thenReturn(Optional.of(todo1));

        Optional<Todo> result = todoService.findByUserIdAndId(1L, 1L);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("findByUserIdAndId - without result")
    void test2_findByUserIdAndId() {
        Optional<Todo> result = todoService.findByUserIdAndId(1L, 1L);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void createOrUpdate() {
        Mockito.when(todoRepository.save(todo1)).thenReturn(todo1);

        Todo result = todoService.createOrUpdate(todo1);

        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("delete - with result")
    void test1_delete() {
        Mockito.when(todoRepository.findByUserIdAndId(1L, 1L)).thenReturn(Optional.of(todo1));

        todoService.delete(1L, 1L);
    }

    @Test
    @DisplayName("delete - without result")
    void test2_delete() {
        todoService.delete(1L, 1L);
    }

}