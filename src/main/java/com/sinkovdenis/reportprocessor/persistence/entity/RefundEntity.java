package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "REFUNDS")
public class RefundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REFUNDS_SEQ")
    @SequenceGenerator(name = "REFUNDS_SEQ", sequenceName = "SEQ_REFUNDS_ID", allocationSize = 1)
    private long id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @Column(name = "REASON")
    @Basic(optional = false)
    private String reason;
}
