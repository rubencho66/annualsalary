package com.mas.global.calculatesalary.facade;

import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.response.ContractResponse;

import java.util.List;

public interface ContractFacade {

    ContractResponse createContract(ContractRequest requestDTO);

    ContractResponse updateContract(long contractNumber, ContractRequest requestDTO);

    List<ContractResponse> findAll();
}
