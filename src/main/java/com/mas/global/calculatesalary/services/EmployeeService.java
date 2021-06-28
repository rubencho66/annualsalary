package com.mas.global.calculatesalary.services;

import com.mas.global.calculatesalary.api.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employee);

    EmployeeDTO updateEmployee(EmployeeDTO employee, long identification);

    List<EmployeeDTO> findAllActive();

    EmployeeDTO findByIdentification(long identification);
}
