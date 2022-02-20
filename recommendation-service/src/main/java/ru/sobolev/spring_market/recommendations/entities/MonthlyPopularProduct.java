package ru.sobolev.spring_market.recommendations.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_popular")
@Data
@NoArgsConstructor
public class MonthlyPopularProduct {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_id")
    private Long productId;

    @CreationTimestamp
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public MonthlyPopularProduct(Long productId) {
        this.productId = productId;
    }
}
