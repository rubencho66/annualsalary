package com.mas.global.calculatesalary.services;

import com.mas.global.calculatesalary.api.dto.ContractDTO;

import java.util.List;

public interface ContractService {

    ContractDTO createContract(ContractDTO contract, long employeeId);

    ContractDTO updateContract(ContractDTO contract, long contractNumber);

    List<ContractDTO> findAll();
}
