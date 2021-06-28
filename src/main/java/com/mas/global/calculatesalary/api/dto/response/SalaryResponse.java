package com.mas.global.calculatesalary.api.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaryResponse {

    private String fullName;

    private long identification;

    private double salary;

    private double annualSalary;

    private String typeContract;

}
