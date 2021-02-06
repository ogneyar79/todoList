package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private Timestamp created;
    private boolean done;

    public Task() {
        this.created = new Timestamp(System.currentTimeMillis());
        this.done = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }
}
