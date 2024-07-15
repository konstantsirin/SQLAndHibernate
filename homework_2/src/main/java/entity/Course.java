package entity;

import entity.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="Courses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    private CourseType type;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
        joinColumns = {@JoinColumn(name= "course_id")},
        inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;
}
