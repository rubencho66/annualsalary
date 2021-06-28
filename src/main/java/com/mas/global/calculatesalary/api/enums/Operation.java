package com.mas.global.calculatesalary.api.enums;

public enum Operation {

    SUCCESSFUL("Successful operation"),
    FAILED("Operation failed");

    private String description;

    public String getDescription() {
        return description;
    }

    Operation(String description) {
        this.description = description;
    }
}
