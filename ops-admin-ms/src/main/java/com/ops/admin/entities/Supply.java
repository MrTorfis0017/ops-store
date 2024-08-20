package com.ops.admin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "SUPPLY")
public class Supply {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "DELIVERED_DATE")
    private Date deliveredDate;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "RECEIVED_BY")
    private Long receivedBy;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @OneToMany(mappedBy = "supply")
    private List<SupplyItem> supplyItems;
}