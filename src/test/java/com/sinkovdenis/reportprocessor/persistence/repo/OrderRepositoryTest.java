package com.sinkovdenis.reportprocessor.persistence.repo;

import com.sinkovdenis.reportprocessor.GenericNoKafkaTest;
import com.sinkovdenis.reportprocessor.GenericTest;
import com.sinkovdenis.reportprocessor.persistence.entity.OrderEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;

import static com.sinkovdenis.reportprocessor.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderRepositoryTest extends GenericNoKafkaTest {

    @Autowired
    private OrderRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void testSave_and_findBy() {
        OrderEntity orderEntity = createOrderEntity("order1");
        OrderEntity savedOrder = repository.save(orderEntity);
        assertThat(savedOrder).isNotNull();

        OrderEntity foundOrder = repository.findById(savedOrder.getId()).get();
        assertThat(foundOrder).isNotNull();
        assertThat(savedOrder).isEqualTo(foundOrder);
    }

    @Test
    public void testFindAll() {
        repository.save(createOrderEntity("order1"));
        repository.save(createOrderEntity("order2"));

        assertThat(repository.findAll())
                .isNotEmpty()
                .hasSize(2);
    }
    
    @Test
    public void testFindByCreationDateBetween() {
        OrderEntity orderEntity = createOrderEntity("order1");
        orderEntity.setCreationDate(new Date(DATE_FROM.getTime() - 1000));

        OrderEntity orderEntity2 = createOrderEntity("order2");
        orderEntity2.setCreationDate(DATE_FROM);

        OrderEntity orderEntity3 = createOrderEntity("order3");
        orderEntity3.setCreationDate(new Date(DATE_TO.getTime() + 1000));
        
        repository.saveAll(Arrays.asList(orderEntity, orderEntity2, orderEntity3));

        assertThat(repository.findByCreationDateBetween(DATE_FROM, DATE_TO))
                .hasSize(1)
                .contains(orderEntity2);
    }

    @Test
    public void testFindByIdIn() {
        OrderEntity orderEntity = createOrderEntity("order1");
        OrderEntity orderEntity2 = createOrderEntity("order2");
        OrderEntity orderEntity3 = createOrderEntity("order3");

        repository.saveAll(Arrays.asList(orderEntity, orderEntity2, orderEntity3));

        assertThat(repository.findByIdIn(Arrays.asList("order0", "order2", "order4")))
                .hasSize(1)
                .contains(orderEntity2);
    }
}
