package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.response.ContractResponse;
import com.mas.global.calculatesalary.api.enums.Operation;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import com.mas.global.calculatesalary.facade.ContractFacade;
import com.mas.global.calculatesalary.services.ContractService;
import com.mas.global.calculatesalary.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ContractFacadeImpl implements ContractFacade {

    private final ContractService contractService;
    private final EmployeeService employeeService;

    public ContractFacadeImpl(ContractService contractService, EmployeeService employeeService) {
        this.contractService = contractService;
        this.employeeService = employeeService;
    }

    @Override
    public ContractResponse createContract(final ContractRequest requestDTO) {
        return buildContractResponse(contractService.createContract(buildContractDto(requestDTO), requestDTO.getEmployeeId()));
    }

    @Override
    public ContractResponse updateContract(final long contractNumber, final ContractRequest requestDTO) {
        final ContractDTO contractDTO = buildContractDto(requestDTO);
        contractDTO.setEmployeeDTO(employeeService.findByIdentification(requestDTO.getEmployeeId()));
        return buildContractResponse(contractService.updateContract(contractDTO, contractNumber));
    }

    @Override
    public List<ContractResponse> findAll() {
        return contractService.findAll().stream().map(this::buildContractResponse).collect(toList());
    }

    private ContractDTO buildContractDto(final ContractRequest requestDTO) {
        ContractDTO contractDto = new ContractDTO();
        contractDto.setTypeContract(requestDTO.getTypeContract().getDescription());
        contractDto.setContractNumber(requestDTO.getContractNumber());
        contractDto.setActive(requestDTO.isActive());
        contractDto.setSalary(requestDTO.getSalary());
        return contractDto;
    }

    private ContractResponse buildContractResponse(final ContractDTO contractDto) {
        ContractResponse response = new ContractResponse();

        if (Objects.nonNull(contractDto)) {
            final Optional<TypeContract> optionalTypeContract = findByDescription(contractDto.getTypeContract());
            final EmployeeDTO employeeDTO = contractDto.getEmployeeDTO();
            response.setTypeContract(optionalTypeContract.isPresent() ? optionalTypeContract.get() : null);
            response.setEmployeeId(employeeDTO.getIdentification());
            response.setNameEmployee(employeeDTO.getFullName());
            response.setActive(contractDto.isActive());
            response.setContractNumber(contractDto.getContractNumber());
            response.setSalary(contractDto.getSalary());
            response.setOperation(Operation.SUCCESSFUL.getDescription());
        } else {
            response.setOperation(Operation.FAILED.getDescription());
        }
        return response;
    }

    private Optional<TypeContract> findByDescription(final String description) {
        return Arrays.stream(TypeContract.values()).filter(tc -> tc.getDescription().equals(description)).findFirst();
    }
}
