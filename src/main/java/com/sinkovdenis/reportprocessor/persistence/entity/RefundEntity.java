package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "REFUNDS")
public class RefundEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;

    @Column(name = "ORDER_ID")
    @Basic(optional = false)
    private String orderId;

    @Column(name = "REASON")
    @Basic(optional = false)
    private String reason;
}
