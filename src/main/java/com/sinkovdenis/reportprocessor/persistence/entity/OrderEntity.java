package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"refunds"})
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<OrderPositionEntity> orderPositions;

    @Column(name = "CUSTOMER_ID")
    @Basic(optional = false)
    private String customerId;

    @Column(name = "SUM")
    @Basic(optional = false)
    private int sum;
    
    @Column(name = "ADDRESS")
    @Basic(optional = false)
    private String address;
    
    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<RefundEntity> refunds;
}
