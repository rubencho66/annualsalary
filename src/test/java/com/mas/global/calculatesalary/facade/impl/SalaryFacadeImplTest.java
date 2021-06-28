package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.ContractDTO;
import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.response.SalaryResponse;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import com.mas.global.calculatesalary.services.EmployeeService;
import com.mas.global.calculatesalary.services.SalaryService;
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
public class SalaryFacadeImplTest {

    private SalaryFacadeImpl salaryFacade;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private SalaryService salaryService;

    @BeforeEach
    public void setUp() {
        salaryFacade = new SalaryFacadeImpl(employeeService, salaryService);
    }

    @Test
    public void calculateAllSalarySuccessfulTest() {
        final double annual = 1000.0;
        Mockito.when(salaryService.calculateSalaryByType(any(TypeContract.class), any(Double.class))).thenReturn(annual);
        Mockito.when(employeeService.findAllActive()).thenReturn(Arrays.asList(buildEmployeeDTO()));
        final List<SalaryResponse> salaryResponses = salaryFacade.calculateAllSalary();
        assertNotNull(salaryResponses);
        assertEquals(1, salaryResponses.size());
        assertTrue(salaryResponses.stream().findFirst().isPresent());
        final SalaryResponse response = salaryResponses.stream().findFirst().get();
        assertEquals(annual, response.getAnnualSalary());

    }

    @Test
    public void calculateSalaryByIdentificationErrorTest() {
        final EmployeeDTO employeeDTO = buildEmployeeDTO();
        final int identification = 123;
        employeeDTO.getContractList().stream().forEach(c -> c.setActive(false));
        Mockito.when(employeeService.findByIdentification(identification)).thenReturn(employeeDTO);
        assertThrows(RuntimeException.class, () ->  salaryFacade.calculateSalaryByIdentification(identification) );
    }


    private EmployeeDTO buildEmployeeDTO() {
        final EmployeeDTO employeeDTO = new EmployeeDTO();
        ContractDTO dto = new ContractDTO();
        dto.setActive(true);
        dto.setTypeContract(TypeContract.MONTHLY_SALARY.getDescription());
        dto.setSalary(200);
        dto.setContractNumber(10);
        dto.setDateCreation(new Date());
        dto.setId(1789L);

        employeeDTO.setIdentification(123);
        employeeDTO.setFullName("MAS Global");
        employeeDTO.setContractList(Arrays.asList(dto));
        return employeeDTO;
    }
}
