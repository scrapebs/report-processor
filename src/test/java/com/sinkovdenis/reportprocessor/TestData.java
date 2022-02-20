package com.sinkovdenis.reportprocessor;

import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.OrderEntity;
import com.sinkovdenis.reportprocessor.persistence.entity.OrderPositionEntity;
import com.sinkovdenis.reportprocessor.persistence.entity.ProductEntity;
import com.sinkovdenis.reportprocessor.persistence.entity.RefundEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestData {

    public static final Date DATE_FROM = Date.from(LocalDateTime.of(2021, 07, 01, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static final Date DATE_TO = Date.from(LocalDateTime.of(2021, 07, 02, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static List<String> REQUESTED_IDS = Arrays.asList("1111", "2222", "3333");
    public static String EMAIL_FOR_RESPONSE = "email@gmail.com";

    public static ByDateReportRequest createByDateReportRequest(ReportType reportType) {
        return ByDateReportRequest.builder()
                .reportType(reportType)
                .from(DATE_FROM)
                .to(DATE_TO)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }

    public static ByIdsReportRequest createByIdsReportRequest(ReportType reportType) {
        return ByIdsReportRequest.builder()
                .reportType(reportType)
                .ids(REQUESTED_IDS)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }
    
    public static ProductEntity createProductEntity(String productName) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productName);
        productEntity.setCreationDate(new Date());
        productEntity.setPrice(10);
        return productEntity;
    }

    public static OrderPositionEntity createOrderPositionEntity(OrderEntity orderEntity, ProductEntity productEntity, int quantity) {
        OrderPositionEntity orderPositionEntity = new OrderPositionEntity();
        orderPositionEntity.setOrder(orderEntity);
        orderPositionEntity.setProduct(productEntity);
        orderPositionEntity.setProductQuantity(quantity);
        return orderPositionEntity;
    }

    public static OrderEntity createOrderEntity(String orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setCreationDate(Date.from(Instant.parse("2021-07-02T00:00:00.00Z")));
        orderEntity.setCustomerId("customerId");
        orderEntity.setSum(100);

        OrderPositionEntity position1 = createOrderPositionEntity(orderEntity, createProductEntity("product1"), 1);
        OrderPositionEntity position2 = createOrderPositionEntity(orderEntity, createProductEntity("product2"), 1);

        orderEntity.setOrderPositions(Arrays.asList(position1, position2));
        return orderEntity;
    }
    
    public static ProcessingEvent createProcessingEvent() {
        return ProcessingEvent.builder()
                .requestId(100L)
                .status(ProcessingEvent.ProcessingEventStatus.SUCCESS)
                .build();
    }

}