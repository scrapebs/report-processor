package com.sinkovdenis.reportprocessor.persistence.repo;

import com.sinkovdenis.reportprocessor.persistence.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

    List<OrderEntity> findByCreationDateBetween(Date from, Date to);
    List<OrderEntity> findByIdIn(List<String> ids);
}
