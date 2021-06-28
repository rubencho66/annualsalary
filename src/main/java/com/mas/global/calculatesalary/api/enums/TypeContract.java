package com.mas.global.calculatesalary.api.enums;

public enum TypeContract {

    HOURLY_SALARY("Hourly Salary Contract"),
    MONTHLY_SALARY("Monthly Salary Contract");


    private String description;

    public String getDescription() {
        return description;
    }

    TypeContract(String description) {
        this.description = description;
    }
}
