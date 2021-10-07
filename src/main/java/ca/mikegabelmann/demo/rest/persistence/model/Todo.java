package ca.mikegabelmann.demo.rest.persistence.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author mgabe
 */
@Entity
@Table(name = "TODO")
public class Todo {
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @SequenceGenerator(name = "seq_todo", sequenceName = "todo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_todo")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "TASK", nullable = false)
    private String task;

    @Column(name = "CREATED_DTM", columnDefinition="TIMESTAMP", nullable = false)
    private LocalDateTime createdDtm;

    @Column(name = "COMPLETED_DTM", columnDefinition="TIMESTAMP")
    private LocalDateTime completedDtm;

    @Version
    @Column(name = "REVISION", nullable = false)
    private Long revision;


    /** Default contstructor. */
    public Todo() {
        this.id = null;
        this.userId = null;
        this.task = "";
        this.createdDtm = LocalDateTime.now();
        this.completedDtm = null;
        this.revision = 1L;
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

    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(LocalDateTime createdDtm) {
        this.createdDtm = createdDtm;
    }

    public LocalDateTime getCompletedDtm() {
        return completedDtm;
    }

    public void setCompletedDtm(LocalDateTime completedDtm) {
        this.completedDtm = completedDtm;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Todo{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", task='").append(task).append('\'');
        sb.append(", createdDtm=").append(createdDtm);
        sb.append(", completedDtm=").append(completedDtm);
        sb.append(", revision=").append(revision);
        sb.append('}');
        return sb.toString();
    }
}
