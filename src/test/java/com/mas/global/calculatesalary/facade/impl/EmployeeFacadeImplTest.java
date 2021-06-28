package com.mas.global.calculatesalary.facade.impl;

import com.mas.global.calculatesalary.api.dto.EmployeeDTO;
import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.api.dto.response.EmployeeResponse;
import com.mas.global.calculatesalary.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeFacadeImplTest {

    private EmployeeFacadeImpl employeeFacade;

    @Mock
    private EmployeeService service;

    @BeforeEach
    public void setUp() {
        employeeFacade = new EmployeeFacadeImpl(service);
    }

    @Test
    public void createEmployeeSuccessfulTest() {
        final EmployeeRequest requestDTO = buildEmployeeRequest();
        Mockito.when(service.createEmployee(any(EmployeeDTO.class))).thenReturn(buildEmployeeDTO(requestDTO));
        final EmployeeResponse employee = employeeFacade.createEmployee(requestDTO);
        assertNotNull(employee);
        assertEquals(requestDTO.getIdentification(), employee.getIdentification());
        assertEquals(requestDTO.getPhone(), employee.getPhone());
        assertEquals(requestDTO.isActive(), employee.isActive());
    }

    @Test
    public void createEmployeeErrorTest() {
        final EmployeeRequest requestDTO = buildEmployeeRequest();
        Mockito.when(service.createEmployee(any(EmployeeDTO.class))).thenThrow(new RuntimeException("Test Error"));
        assertThrows(RuntimeException.class, () -> employeeFacade.createEmployee(requestDTO));
    }

    @Test
    public void findAllSuccessfulTest() {
        final EmployeeRequest requestDTO = buildEmployeeRequest();
        Mockito.when(service.findAllActive()).thenReturn(Arrays.asList(buildEmployeeDTO(requestDTO)));
        final List<EmployeeResponse> employeeList = employeeFacade.findAll();
        assertNotNull(employeeList);
        assertEquals(1, employeeList.size());
        assertTrue(employeeList.stream().findFirst().isPresent());
    }

    @Test
    public void updateEmployeeSuccessfulTest() {
        final EmployeeRequest requestDTO = buildEmployeeRequest();
        final int identification = 123;
        Mockito.when(service.updateEmployee(any(EmployeeDTO.class), any(Long.class))).thenReturn(buildEmployeeDTO(requestDTO));
        final EmployeeResponse employee = employeeFacade.updateEmployee(identification, requestDTO);
        assertNotNull(employee);
        assertEquals(requestDTO.getIdentification(), employee.getIdentification());
        assertEquals(requestDTO.getPhone(), employee.getPhone());
        assertEquals(requestDTO.isActive(), employee.isActive());
    }

    @Test
    public void updateEmployeeErrorTest() {
        final EmployeeRequest requestDTO = buildEmployeeRequest();
        Mockito.when(service.updateEmployee(any(EmployeeDTO.class), any(Long.class))).thenReturn(null);
        assertThrows(RuntimeException.class, () -> employeeFacade.updateEmployee(123, requestDTO));
    }

    private EmployeeRequest buildEmployeeRequest() {
        EmployeeRequest request = new EmployeeRequest();
        request.setActive(true);
        request.setDirection("Street");
        request.setFullName("MAS Global");
        request.setIdentification(999);
        request.setPhone("555-333-222");

        return request;
    }

    private EmployeeDTO buildEmployeeDTO(EmployeeRequest requestDTO) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setPhone(requestDTO.getPhone());
        dto.setDirection(requestDTO.getDirection());
        dto.setIdentification(requestDTO.getIdentification());
        dto.setActive(requestDTO.isActive());
        dto.setId(123L);
        dto.setLastUpdate(new Date());
        dto.setContractList(new ArrayList<>());
        dto.setFullName(requestDTO.getFullName());
        return dto;
    }
}
