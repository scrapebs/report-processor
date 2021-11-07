package com.sinkovdenis.reportprocessor.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REFUNDS")
public class RefundEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;
}
