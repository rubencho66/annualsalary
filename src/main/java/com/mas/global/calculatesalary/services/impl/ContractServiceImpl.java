package com.mas.global.calculatesalary.services.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.mapper.CustomModelMapper;
import com.mas.global.calculatesalary.repository.ContractRepository;
import com.mas.global.calculatesalary.repository.EmployeeRepository;
import com.mas.global.calculatesalary.repository.model.Contract;
import com.mas.global.calculatesalary.repository.model.Employee;
import com.mas.global.calculatesalary.services.ContractService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomModelMapper mapper;

    public ContractServiceImpl(ContractRepository contractRepository, EmployeeRepository employeeRepository, CustomModelMapper mapper) {
        this.contractRepository = contractRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ContractDTO createContract(final ContractDTO contractDto, final long employeeId) {
        final Optional<Employee> employee = employeeRepository.findByIdentification(employeeId);
        if (employee.isPresent()) {
            Contract contract = convert(contractDto, Contract.class);
            contract.setEmployee(employee.get());
            contract.setDateCreation(new Date());
            return buildFullContract(contract);
        }
        throw new RuntimeException("No employee with ID: " + employeeId + " was found");
    }

    private ContractDTO buildFullContract(Contract contract) {
        final Contract saved = contractRepository.save(contract);
        final ContractDTO contractDTO = convert(saved, ContractDTO.class);
        contractDTO.setEmployeeDTO(convert(saved.getEmployee(), EmployeeDTO.class));
        return contractDTO;
    }

    @Override
    public ContractDTO updateContract(final ContractDTO contractDto, final long contractNumber) {
        final Optional<Contract> contractOptional = contractRepository.findByContractNumber(contractNumber);
        if (contractOptional.isPresent()) {
            Contract contract = contractOptional.get();
            contract.setTypeContract(contractDto.getTypeContract());
            contract.setContractNumber(contractDto.getContractNumber());
            contract.setSalary(contractDto.getSalary());
            contract.setEmployee(convert(contractDto.getEmployeeDTO(), Employee.class));
            return buildFullContract(contractRepository.save(contract));
        }
        throw new RuntimeException("No contract with contract number: " + contractNumber + " was found");
    }

    @Override
    public List<ContractDTO> findAll() {
        return contractRepository.findAll().stream()
                .filter(Contract::isActive)
                .map(c-> {
                    final ContractDTO contractDTO = convert(c, ContractDTO.class);
                    contractDTO.setEmployeeDTO(convert(c.getEmployee(), EmployeeDTO.class));
                    return contractDTO;
                })
                .collect(Collectors.toList());
    }

    private <T> T convert(Object obj, Class<?> type) {
        return mapper.map(obj, (Type) type);
    }

}
