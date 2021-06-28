package com.mas.global.calculatesalary.strategy.impl;

import com.mas.global.calculatesalary.strategy.SalaryStrategy;
import org.springframework.stereotype.Service;

@Service("MONTHLY_SALARY")
public class SalaryMonthtlyStrategyImpl implements SalaryStrategy {

    @Override
    public double calculateAnnualPayment(final double salary) {
        return salary * 12;
    }
}
