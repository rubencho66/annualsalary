package com.mas.global.calculatesalary.services;

import com.mas.global.calculatesalary.api.enums.TypeContract;

public interface SalaryService {

    double calculateSalaryByType(TypeContract typeContract, double salary);
}
