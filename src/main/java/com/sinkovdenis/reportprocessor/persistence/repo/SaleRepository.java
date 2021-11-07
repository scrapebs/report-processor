package com.sinkovdenis.reportprocessor.persistence.repo;

import com.sinkovdenis.reportprocessor.persistence.entity.SaleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends CrudRepository<SaleEntity, String> {

    List<SaleEntity> findByCreationDateBetween(Date from, Date to);
    List<SaleEntity> findByIdIn(List<String> ids);
}
