package com.mas.global.calculatesalary.repository.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "contract_number", nullable = false)
    private long contractNumber;

    @Column(name = "type_contract", nullable = false)
    private String typeContract;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "date_creation", nullable = false)
    private Date dateCreation;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;
}
