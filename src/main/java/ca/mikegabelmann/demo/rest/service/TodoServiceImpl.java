package ca.mikegabelmann.demo.rest.service;

import ca.mikegabelmann.demo.rest.persistence.model.Todo;
import ca.mikegabelmann.demo.rest.persistence.repository.TodoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(TodoServiceImpl.class);

    private final TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Todo> findByUserId(final long userId) {
        LOG.debug("findByUserId : userId={}", userId);

        return todoRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Todo> findByUserIdAndId(final long userId, final long id) {
        LOG.debug("findByUserIdAndId : userId={}, id={}", userId, id);

        return todoRepository.findByUserIdAndId(userId, id);
    }

    @Override
    public void delete(final long userId, final long id) {
        LOG.debug("delete : userId={}, id={}", userId, id);

        Optional<Todo> record = this.findByUserIdAndId(userId, id);

        if (record.isPresent()) {
            todoRepository.delete(record.get());

        } else {
            LOG.warn("delete : userId={}, id={} - record not found", userId , id);
        }
    }

    @Override
    public Todo createOrUpdate(final Todo todo) {
        LOG.debug("createOrUpdate : todo={}", todo);

        return todoRepository.save(todo);
    }

}
