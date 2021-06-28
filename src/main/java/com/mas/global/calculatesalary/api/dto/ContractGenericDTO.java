package com.mas.global.calculatesalary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractGenericDTO {

    private long contractNumber;

    private double salary;

    private boolean active;

}
