package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Teachers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer salary;

    private Integer age;
}
