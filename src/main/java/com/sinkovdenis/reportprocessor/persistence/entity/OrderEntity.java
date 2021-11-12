package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;

    @Column(name = "PRODUCT_ID")
    @Basic(optional = false)
    private String productId;

    @Column(name = "CUSTOMER_ID")
    @Basic(optional = false)
    private String customerId;

    @Column(name = "SUM")
    @Basic(optional = false)
    private Integer sum;
}
