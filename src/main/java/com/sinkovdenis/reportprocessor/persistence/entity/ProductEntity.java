package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "PRODUCTS")
public class ProductEntity {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_SEQ")
    @SequenceGenerator(name = "PRODUCTS_SEQ", sequenceName = "SEQ_PRODUCTS_ID", allocationSize = 1)
    private long id;
    
    @Column(name = "PRODUCT_NAME")
    @Basic(optional = false)
    private String productName;
    
    @Column(name = "PRICE")
    @Basic(optional = false)
    private double price;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;
}
