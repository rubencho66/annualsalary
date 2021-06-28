package com.mas.global.calculatesalary.strategy.impl;

import com.mas.global.calculatesalary.strategy.SalaryStrategy;
import org.springframework.stereotype.Service;


@Service("HOURLY_SALARY")
public class SalaryHourlyStrategyImpl implements SalaryStrategy {

    @Override
    public double calculateAnnualPayment(final double salary) {
        return 120 * salary * 12;
    }
}
