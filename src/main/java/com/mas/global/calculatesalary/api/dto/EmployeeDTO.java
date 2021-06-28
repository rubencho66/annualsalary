package com.mas.global.calculatesalary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends EmployeeGenericDTO {

    private Long id;

    private Date lastUpdate;

    private List<ContractDTO> contractList;

}
