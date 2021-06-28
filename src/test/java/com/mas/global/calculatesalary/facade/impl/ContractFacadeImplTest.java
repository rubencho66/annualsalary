package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.response.ContractResponse;
import com.mas.global.calculatesalary.api.enums.Operation;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import com.mas.global.calculatesalary.services.ContractService;
import com.mas.global.calculatesalary.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ContractFacadeImplTest {

    private ContractFacadeImpl contractFacade;

    @Mock
    private ContractService contractService;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        contractFacade = new ContractFacadeImpl(contractService, employeeService);
    }

    @Test
    public void findAllSuccessfulTest() {
        Mockito.when(contractService.findAll()).thenReturn(Arrays.asList(buildContractDTO(buildContractRequest())));
        final List<ContractResponse> all = contractFacade.findAll();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertTrue(all.stream().findFirst().isPresent());

    }

    @Test
    public void createContractSuccessfulTest() {
        final ContractRequest request = buildContractRequest();
        final ContractDTO contractDTO = buildContractDTO(request);
        Mockito.when(contractService.createContract(any(ContractDTO.class), any(Long.class))).thenReturn(contractDTO);
        final ContractResponse response = contractFacade.createContract(request);
        assertNotNull(response);
        assertEquals(request.getContractNumber(), response.getContractNumber());
        assertEquals(request.getEmployeeId(), response.getEmployeeId());
        assertEquals(request.getSalary(), response.getSalary());
    }

    @Test
    public void updateContractSuccessfulTest() {
        final ContractRequest request = buildContractRequest();
        final ContractDTO contractDTO = buildContractDTO(request);
        Mockito.when(contractService.updateContract(any(ContractDTO.class), any(Long.class))).thenReturn(contractDTO);
        final ContractResponse response = contractFacade.updateContract(123, request);
        assertNotNull(response);
        assertEquals(request.getContractNumber(), response.getContractNumber());
        assertEquals(request.getEmployeeId(), response.getEmployeeId());
        assertEquals(request.getSalary(), response.getSalary());
    }

    @Test
    public void updateContractErrorTest() {
        final ContractRequest request = buildContractRequest();
        Mockito.when(contractService.updateContract(any(ContractDTO.class), any(Long.class))).thenReturn(null);
        final ContractResponse response = contractFacade.updateContract(123, request);
        assertEquals(Operation.FAILED.getDescription(), response.getOperation());
    }

    private ContractRequest buildContractRequest() {
        ContractRequest request = new ContractRequest();
        request.setActive(true);
        request.setTypeContract(TypeContract.MONTHLY_SALARY);
        request.setEmployeeId(999);
        request.setContractNumber(10);
        request.setSalary(2000);

        return request;
    }

    private ContractDTO buildContractDTO(ContractRequest request) {
        ContractDTO dto = new ContractDTO();
        final EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setIdentification(request.getEmployeeId());
        dto.setEmployeeDTO(employeeDTO);
        dto.setActive(request.isActive());
        dto.setTypeContract(request.getTypeContract().getDescription());
        dto.setSalary(request.getSalary());
        dto.setContractNumber(request.getContractNumber());
        dto.setDateCreation(new Date());
        dto.setId(1789L);
        return dto;
    }
}
