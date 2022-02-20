package ru.sobolev.spring_market.test.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.sobolev.spring_market.core.repositories.OrdersRepository;

@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void testFindOrdersByUsername(){
        Assertions.assertEquals(1 , ordersRepository.findAllByUsername("bob").size());
    }

}
