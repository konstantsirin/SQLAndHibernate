package entity;

import entity.compositeKeys.SubscriptionKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="LinkedPurchaseList")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedPurchase {
    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "student_id", nullable = false, insertable = false, updatable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false, insertable = false, updatable = false)
    private Long courseId;
}
