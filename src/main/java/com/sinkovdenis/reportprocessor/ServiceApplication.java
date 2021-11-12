package com.sinkovdenis.reportprocessor;

import com.sinkovdenis.reportprocessor.persistence.entity.OrderEntity;
import com.sinkovdenis.reportprocessor.persistence.entity.RefundEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.OrderRepository;
import com.sinkovdenis.reportprocessor.persistence.repo.RefundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner testEntities(OrderRepository orderRepository, RefundRepository refundRepository){
		return (args) ->{
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setId("1");
			orderEntity.setCreationDate(Date.from(Instant.parse("2021-07-01T12:00:00.00Z")));
			orderEntity.setProductId("111");
			orderEntity.setCustomerId("111");
			orderEntity.setSum(10);
			orderRepository.save(orderEntity);

			orderEntity.setId("2");
			orderEntity.setCreationDate(Date.from(Instant.parse("2021-07-02T00:00:00.00Z")));
			orderEntity.setProductId("222");
			orderEntity.setCustomerId("222");
			orderEntity.setSum(100);
			orderRepository.save(orderEntity);

			orderEntity.setId("3");
			orderEntity.setCreationDate(Date.from(Instant.parse("2021-07-03T12:00:00.00Z")));
			orderEntity.setProductId("333");
			orderEntity.setCustomerId("333");
			orderEntity.setSum(1000);
			orderRepository.save(orderEntity);


			RefundEntity refundEntity = new RefundEntity();
			refundEntity.setId("1");
			refundEntity.setCreationDate(Date.from(Instant.parse("2021-07-01T12:00:00.00Z")));
			refundEntity.setOrderId("111");
			refundEntity.setReason("broken");
			refundRepository.save(refundEntity);

			refundEntity.setId("2");
			refundEntity.setCreationDate(Date.from(Instant.parse("2021-07-02T00:00:00.00Z")));
			refundEntity.setOrderId("222");
			refundEntity.setReason("broken");
			refundRepository.save(refundEntity);

			refundEntity.setId("3");
			refundEntity.setCreationDate(Date.from(Instant.parse("2021-07-03T12:00:00.00Z")));
			refundEntity.setOrderId("333");
			refundEntity.setReason("broken");
			refundRepository.save(refundEntity);
		};
	}

}
