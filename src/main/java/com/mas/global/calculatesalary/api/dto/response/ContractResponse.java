package com.mas.global.calculatesalary.api.dto.response;

import com.mas.global.calculatesalary.api.dto.ContractGenericDTO;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse extends ContractGenericDTO {

    private String nameEmployee;

    private TypeContract typeContract;

    private long employeeId;

    private String operation;
}
