package com.mas.global.calculatesalary.facade;

import com.mas.global.calculatesalary.api.dto.response.SalaryResponse;

import java.util.List;

public interface SalaryFacade {

    List<SalaryResponse> calculateAllSalary();

    SalaryResponse calculateSalaryByIdentification(long identification);
}
