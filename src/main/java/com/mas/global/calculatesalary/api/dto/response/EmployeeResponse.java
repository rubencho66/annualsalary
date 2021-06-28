package com.mas.global.calculatesalary.api.dto.response;


import com.mas.global.calculatesalary.api.dto.EmployeeGenericDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse extends EmployeeGenericDTO {

    private String operation;
}
