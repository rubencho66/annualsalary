package com.mas.global.calculatesalary.api.controller;


import com.mas.global.calculatesalary.api.dto.response.SalaryResponse;
import com.mas.global.calculatesalary.facade.SalaryFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruben Triana
 *
 * This controller allows to obtain the calculation of an employee's salary
 */
@RestController
@RequestMapping(path = "/api/salary")
public class SalaryController {

    private final SalaryFacade facade;

    public SalaryController(SalaryFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/{identification}/calculate")
    @Operation(description = "Find all Contracts", summary = "This service get all Contracts active.")
    public ResponseEntity<SalaryResponse> calculateSalaryByIdentification(@PathVariable("identification") final long identification) {
        return ResponseEntity.ok(facade.calculateSalaryByIdentification(identification));
    }

    @GetMapping("/all/calculate")
    @Operation(description = "Find all Contracts", summary = "This service get all Contracts active.")
    public ResponseEntity<List<SalaryResponse>> calculateAllSalary() {
        return ResponseEntity.ok(facade.calculateAllSalary());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedServerError(RuntimeException ex) {
        return ex.getMessage();
    }
}
