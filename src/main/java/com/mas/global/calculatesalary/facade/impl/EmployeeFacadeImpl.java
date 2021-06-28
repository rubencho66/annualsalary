package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.api.dto.response.EmployeeResponse;
import com.mas.global.calculatesalary.api.enums.Operation;
import com.mas.global.calculatesalary.facade.EmployeeFacade;
import com.mas.global.calculatesalary.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService service;

    public EmployeeFacadeImpl(EmployeeService service) {
        this.service = service;
    }

    @Override
    public EmployeeResponse createEmployee(final EmployeeRequest requestDTO) {
        return buildEmployeeResponse(service.createEmployee(buildEmployeeDto(requestDTO)));
    }

    @Override
    public EmployeeResponse updateEmployee(final long identification, final EmployeeRequest requestDTO) {
        final EmployeeDTO dto = service.updateEmployee(buildEmployeeDto(requestDTO), identification);
        if (Objects.nonNull(dto)) {
            return buildEmployeeResponse(dto);
        }
        throw new RuntimeException("Employee not found with id: " + identification);
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return service.findAllActive().stream().map(this::buildEmployeeResponse).collect(Collectors.toList());
    }

    private EmployeeResponse buildEmployeeResponse(EmployeeDTO employee) {
        final EmployeeResponse responseDTO = new EmployeeResponse();

        if (Objects.nonNull(employee)) {
            responseDTO.setIdentification(employee.getIdentification());
            responseDTO.setFullName(employee.getFullName());
            responseDTO.setDirection(employee.getDirection());
            responseDTO.setPhone(employee.getPhone());
            responseDTO.setActive(employee.isActive());
            responseDTO.setOperation(Operation.SUCCESSFUL.getDescription());
        } else {
            responseDTO.setOperation(Operation.FAILED.getDescription());
        }
        return responseDTO;
    }

    private EmployeeDTO buildEmployeeDto(final EmployeeRequest requestDTO) {

        final EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setIdentification(requestDTO.getIdentification());
        employeeDto.setFullName(requestDTO.getFullName());
        employeeDto.setDirection(requestDTO.getDirection());
        employeeDto.setPhone(requestDTO.getPhone());
        employeeDto.setActive(requestDTO.isActive());

        return employeeDto;
    }
}
