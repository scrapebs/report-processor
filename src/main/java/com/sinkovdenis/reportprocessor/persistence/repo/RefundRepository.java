package com.sinkovdenis.reportprocessor.persistence.repo;

import com.sinkovdenis.reportprocessor.persistence.entity.RefundEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RefundRepository extends CrudRepository<RefundEntity, String> {
    List<RefundEntity> findByCreationDateBetween(Date from, Date to);
    List<RefundEntity> findByIdIn(List<String> ids);
}
