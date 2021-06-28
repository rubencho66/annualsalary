package com.mas.global.calculatesalary.repository.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "identification", nullable = false)
    private long identification;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "direction", nullable = false)
    private String direction;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contractList;
}
