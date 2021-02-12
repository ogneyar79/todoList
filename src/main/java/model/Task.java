package model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private Timestamp created;
    private boolean done;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    public Task() {
        this.created = new Timestamp(System.currentTimeMillis());
        this.done = false;
    }

}
