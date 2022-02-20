package ru.sobolev.spring_market.recommendations.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dayli_popular")
@Data
@NoArgsConstructor
public class DailyPopularProduct {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_id")
    private Long productId;

    @CreationTimestamp
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public DailyPopularProduct(Long productId) {
        this.productId = productId;
    }

}
