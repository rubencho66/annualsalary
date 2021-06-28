package com.mas.global.calculatesalary.services.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.mapper.CustomModelMapper;
import com.mas.global.calculatesalary.repository.EmployeeRepository;
import com.mas.global.calculatesalary.repository.model.Employee;
import com.mas.global.calculatesalary.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final CustomModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, CustomModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDTO createEmployee(final EmployeeDTO dto) {
        dto.setLastUpdate(new Date());
        return convert(repository.save(convert(dto, Employee.class)), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(final EmployeeDTO dto, long identification) {
        final Optional<Employee> empOptional = repository.findByIdentification(identification);
        if (empOptional.isPresent()) {
            final Employee employee = empOptional.get();
            employee.setDirection(dto.getDirection());
            employee.setFullName(dto.getFullName());
            employee.setPhone(dto.getPhone());
            employee.setActive(dto.isActive());
            employee.setIdentification(dto.getIdentification());
            employee.setLastUpdate(new Date());
            return convert(repository.save(employee), EmployeeDTO.class);
        }
        throw new RuntimeException("No employee with identification: " + identification + " was found");
    }

    @Override
    public List<EmployeeDTO> findAllActive() {
        return repository.findAll().stream().filter(Employee::isActive).map(e -> {
            final EmployeeDTO employeeDTO = convert(e, EmployeeDTO.class);
            employeeDTO.setContractList(e.getContractList().stream().map(c -> {
                return (ContractDTO) convert(c, ContractDTO.class);
            }).collect(toList()));
            return employeeDTO;
        }).collect(toList());
    }

    @Override
    public EmployeeDTO findByIdentification(final long identification) {
        final Optional<Employee> empOptional = repository.findByIdentification(identification);
        if (empOptional.isPresent()) {
            final Employee employee = empOptional.get();
            employee.setDirection(employee.getDirection());
            employee.setFullName(employee.getFullName());
            employee.setPhone(employee.getPhone());
            employee.setActive(employee.isActive());
            employee.setIdentification(employee.getIdentification());
            final EmployeeDTO employeeDTO = convert(employee, EmployeeDTO.class);
            employeeDTO.setContractList(employee.getContractList().stream().map(c-> {
                return (ContractDTO) convert(c, ContractDTO.class);
            }).collect(toList()));
            return employeeDTO;
        }
        throw new RuntimeException("No employee with identification: " + identification + " was found");
    }

    private <T> T convert(Object obj, Class<?> type) {
        return mapper.map(obj, (Type) type);
    }

}
