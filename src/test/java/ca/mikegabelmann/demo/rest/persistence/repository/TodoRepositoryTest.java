package ca.mikegabelmann.demo.rest.persistence.repository;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Create in-memory H2 database to test queries.
 * @author mgabe
 */
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private Todo todo1;


    @BeforeEach
    void beforeEach() {
        Todo tmp = new Todo(null, 1L, "task", LocalDateTime.now(), null, 1L);
        this.todo1 = todoRepository.save(tmp);
    }

    @Test
    @DisplayName("findByUserId - with result")
    void test1_findByUserId() {
        List<Todo> results = todoRepository.findByUserId(1L);

        Assertions.assertNotNull(results);
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("findByUserId - without result")
    void test2_findByUserId() {
        List<Todo> results = todoRepository.findByUserId(2L);

        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    @DisplayName("findByUserIdAndId - with result")
    void test1_findByUserIdAndId() {
        Optional<Todo> result = todoRepository.findByUserIdAndId(1L, todo1.getId());

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("findByUserIdAndId - without result")
    void test2_findByUserIdAndId() {
        Optional<Todo> result = todoRepository.findByUserIdAndId(1L, 0L);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

}
