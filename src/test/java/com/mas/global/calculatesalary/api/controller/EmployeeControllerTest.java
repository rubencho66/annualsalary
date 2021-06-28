package com.mas.global.calculatesalary.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.facade.EmployeeFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeFacade facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAllActiveSuccessfulTest() throws Exception {

        mockMvc.perform(get("/api/employee/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllActiveErrorTest() throws Exception {

        Mockito.doThrow(new RuntimeException("Error")).when(facade).findAll();
        mockMvc.perform(get("/api/employee/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void updateContractSuccessfulTest() throws Exception {

        mockMvc.perform(put("/api/employee/{identification}/edit", 11111)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EmployeeRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateContractErrorTest() throws Exception {

        final int identification = 1111;
        Mockito.doThrow(new RuntimeException("Not Found")).when(facade).updateEmployee(identification, new EmployeeRequest());
        mockMvc.perform(put("/api/employee/{identification}/edit", identification)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EmployeeRequest())))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void createContractSuccessfulTest() throws Exception {

        mockMvc.perform(post("/api/employee/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EmployeeRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void createContractErrorTest() throws Exception {

        Mockito.doThrow(new RuntimeException("Error")).when(facade).createEmployee(new EmployeeRequest());
        mockMvc.perform(post("/api/employee/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EmployeeRequest())))
                .andExpect(status().is5xxServerError());
    }

}
