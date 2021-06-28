package com.mas.global.calculatesalary.repository;

import com.mas.global.calculatesalary.repository.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdentification(long identification);
}
