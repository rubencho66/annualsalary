package com.mas.global.calculatesalary.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.facade.ContractFacade;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContractController.class)
public class ContractControllerTest {

    @MockBean
    private ContractFacade facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAllActiveSuccessfulTest() throws Exception {

        mockMvc.perform(get("/api/contract/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllActiveErrorTest() throws Exception {

        Mockito.doThrow(new RuntimeException("Error")).when(facade).findAll();
        mockMvc.perform(get("/api/contract/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void updateContractSuccessfulTest() throws Exception {

        mockMvc.perform(put("/api/contract/{numberContract}/edit", 11111)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EmployeeRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateContractErrorTest() throws Exception {

        final int numberContract = 1111;
        Mockito.doThrow(new RuntimeException("Not Found")).when(facade).updateContract(numberContract, new ContractRequest());
        mockMvc.perform(put("/api/contract/{numberContract}/edit", numberContract)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ContractRequest())))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void createContractSuccessfulTest() throws Exception {

        mockMvc.perform(post("/api/contract/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ContractRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void createContractErrorTest() throws Exception {

        Mockito.doThrow(new RuntimeException("Error")).when(facade).createContract(new ContractRequest());
        mockMvc.perform(post("/api/contract/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ContractRequest())))
                .andExpect(status().is5xxServerError());
    }
}
