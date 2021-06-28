package com.mas.global.calculatesalary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO extends ContractGenericDTO {

    private Long id;

    private Date dateCreation;

    private EmployeeDTO employeeDTO;

    private String typeContract;

}
