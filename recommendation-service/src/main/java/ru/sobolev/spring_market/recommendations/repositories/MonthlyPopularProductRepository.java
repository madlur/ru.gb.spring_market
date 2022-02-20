package ru.sobolev.spring_market.recommendations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sobolev.spring_market.recommendations.entities.MonthlyPopularProduct;

import java.util.List;

@Repository
public interface MonthlyPopularProductRepository extends JpaRepository<MonthlyPopularProduct, Long> {

    @Modifying
    @Query(
            value = "delete from montly_popular where added_at < now() - interval 1 month",
            nativeQuery = true)
    void deleteOldEntries();


    @Query(
            value = "select product_id from montly_popular group by product_id order by count (*) desc limit 5",
            nativeQuery = true)
    List<Long> findFirstFiveWithManyEntries();

}
