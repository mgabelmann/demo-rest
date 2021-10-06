package ca.mikegabelmann.demo.rest.persistence.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
public class Todo {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "TASK", nullable = false)
    private String task;

    @Column(name = "DATE_CREATED", columnDefinition="TIMESTAMP", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_COMPLETED", columnDefinition="TIMESTAMP")
    private LocalDateTime dateCompleted;

    /** Default contstructor. */
    public Todo() {
        this.id = null;
        this.userId = null;
        this.task = "";
        this.dateCreated = LocalDateTime.now();
        this.dateCompleted = null;
    }

    //TODO: add required arguments constructor
    //TODO: add all arguments constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

}
