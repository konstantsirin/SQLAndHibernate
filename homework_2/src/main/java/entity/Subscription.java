package entity;
import entity.compositeKeys.SubscriptionKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Subscriptions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;
}


