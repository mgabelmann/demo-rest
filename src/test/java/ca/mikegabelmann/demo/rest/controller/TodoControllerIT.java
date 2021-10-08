package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.persistence.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for TodoController which also integrates the Service and Repository via H2 database.
 * @author mgabe
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIT {
    @Autowired
    private MockMvc mvc;

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
    void test1_findByUser() throws Exception {
        mvc.perform(get(TodoController.PATH_USER_USERID_TODO, 1))
                .andExpectAll(
                        status().isOk(),
                        content().string(startsWith("[{\"id\":" + todo1.getId() + ","))
                );
    }

    @Test
    @DisplayName("findByUserId - without result")
    void test2_findByUser() throws Exception {
        mvc.perform(get(TodoController.PATH_USER_USERID_TODO, 0))
                .andExpectAll(
                        status().isOk(),
                        content().string(startsWith("[]"))
                );
    }
}
