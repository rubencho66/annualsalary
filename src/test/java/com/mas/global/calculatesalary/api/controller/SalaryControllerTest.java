package com.mas.global.calculatesalary.api.controller;

import com.mas.global.calculatesalary.facade.SalaryFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SalaryController.class)
public class SalaryControllerTest {

    @MockBean
    private SalaryFacade facade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateSalaryByIdentificationSuccessfulTest() throws Exception {

        mockMvc.perform(get("/api/salary/all/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateSalaryByIdentificationErrorTest() throws Exception {

        Mockito.doThrow(new RuntimeException("Error")).when(facade).calculateAllSalary();
        mockMvc.perform(get("/api/salary/all/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void calculateAllSalarySuccessfulTest() throws Exception {

        mockMvc.perform(get("/api/salary/{identification}/calculate", 11111)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateAllSalaryErrorTest() throws Exception {

        final int identification = 1111;
        Mockito.doThrow(new RuntimeException("Not Found")).when(facade).calculateSalaryByIdentification(identification);
        mockMvc.perform(get("/api/salary/{identification}/calculate", identification)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}
