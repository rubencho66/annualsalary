package com.mas.global.calculatesalary.api.dto.request;

import com.mas.global.calculatesalary.api.dto.ContractGenericDTO;
import com.mas.global.calculatesalary.api.enums.TypeContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest extends ContractGenericDTO {

    private long employeeId;

    private TypeContract typeContract;
}
