package entity;

import entity.compositeKeys.PurchaseKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PurchaseList")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @EmbeddedId
    private PurchaseKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private Integer price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;
}
