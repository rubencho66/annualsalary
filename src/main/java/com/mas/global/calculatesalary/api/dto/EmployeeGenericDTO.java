package com.mas.global.calculatesalary.api.dto;

import lombok.Data;

@Data
public class EmployeeGenericDTO {

    private long identification;

    private String fullName;

    private String phone;

    private String direction;

    private boolean active;
}
