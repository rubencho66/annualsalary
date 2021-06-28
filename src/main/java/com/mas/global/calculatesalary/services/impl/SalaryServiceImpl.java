package com.mas.global.calculatesalary.services.impl;

import com.mas.global.calculatesalary.api.enums.TypeContract;
import com.mas.global.calculatesalary.services.SalaryService;
import com.mas.global.calculatesalary.strategy.SalaryStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final ApplicationContext applicationContext;

    public SalaryServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public double calculateSalaryByType(final TypeContract typeContract, final double salary) {
        return applicationContext.getBean(typeContract.name(), SalaryStrategy.class).calculateAnnualPayment(salary);
    }
}
