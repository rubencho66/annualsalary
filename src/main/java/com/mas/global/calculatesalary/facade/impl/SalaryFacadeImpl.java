package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.ContractGenericDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.response.SalaryResponse;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import com.mas.global.calculatesalary.facade.SalaryFacade;
import com.mas.global.calculatesalary.services.EmployeeService;
import com.mas.global.calculatesalary.services.SalaryService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalaryFacadeImpl implements SalaryFacade {

    private final EmployeeService employeeService;

    private final SalaryService salaryService;

    public SalaryFacadeImpl(EmployeeService employeeService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    @Override
    public List<SalaryResponse> calculateAllSalary() {

        List<SalaryResponse> response = new ArrayList<>();
        employeeService.findAllActive().stream().forEach( e -> response.addAll(getResponseList(e)));
        return response;
    }

    @Override
    public SalaryResponse calculateSalaryByIdentification(final long identification) {
        final EmployeeDTO employeeDto = employeeService.findByIdentification(identification);
        final Optional<ContractDTO> contractDTO = employeeDto.getContractList().stream().filter(ContractDTO::isActive).findFirst();
        if (Objects.nonNull(contractDTO)) {
            final ContractDTO dto = contractDTO.get();
            return buildResponse(salaryService.calculateSalaryByType(getTypeByDescription(dto.getTypeContract()), dto.getSalary()), dto, employeeDto);
        }
        throw new RuntimeException("The employee with ID: " + identification + ", does not have an active contract.");
    }

    private List<SalaryResponse> getResponseList(final EmployeeDTO employeeDTO) {
        return employeeDTO.getContractList().stream()
                .filter(ContractGenericDTO::isActive)
                .map(c -> {
                    final double annualSalary = salaryService.calculateSalaryByType(getTypeByDescription(c.getTypeContract()), c.getSalary());
                    return buildResponse(annualSalary, c, employeeDTO);
                }).collect(Collectors.toList());
    }

    private  TypeContract getTypeByDescription(final String description) {
        final Optional<TypeContract> type = Arrays.stream(TypeContract.values()).filter(tc -> tc.getDescription().equals(description)).findFirst();
        return type.isPresent() ? type.get() : null;
    }

    private SalaryResponse buildResponse(final double annualSalary, final ContractDTO contractDTO, final EmployeeDTO employeeDTO) {

        return SalaryResponse.builder().salary(contractDTO.getSalary())
                .annualSalary(annualSalary).fullName(employeeDTO.getFullName())
                .typeContract(contractDTO.getTypeContract())
                .identification(employeeDTO.getIdentification()).build();
    }
}
