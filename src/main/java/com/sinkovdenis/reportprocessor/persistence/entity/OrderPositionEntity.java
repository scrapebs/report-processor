package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"order"})
@Table(name = "ORDER_POSITIONS")
public class OrderPositionEntity {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_POSITIONS_SEQ")
    @SequenceGenerator(name = "ORDER_POSITIONS_SEQ", sequenceName = "SEQ_ORDER_POSITIONS_ID", allocationSize = 1)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;
    
    @Basic(optional = false)
    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;
    
}
