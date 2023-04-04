package service.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private int age;

    @Override
    public String toString() {
        return "ID: "+this.getId()+", USERNAME: "+this.getUsername()+", AGE: "+this.getAge();
    }

}
