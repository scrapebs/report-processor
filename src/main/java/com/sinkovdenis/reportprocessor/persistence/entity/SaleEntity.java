package com.sinkovdenis.reportprocessor.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SALES")
public class SaleEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;

}
