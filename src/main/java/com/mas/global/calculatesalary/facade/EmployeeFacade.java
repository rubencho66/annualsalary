package com.mas.global.calculatesalary.facade;

import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.api.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeFacade {

    EmployeeResponse createEmployee(EmployeeRequest requestDTO);

    EmployeeResponse updateEmployee(long identification, EmployeeRequest requestDTO);

    List<EmployeeResponse> findAll();
}
