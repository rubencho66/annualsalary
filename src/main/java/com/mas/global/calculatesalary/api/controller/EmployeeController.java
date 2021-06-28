package com.mas.global.calculatesalary.api.controller;


import com.mas.global.calculatesalary.api.dto.request.EmployeeRequest;
import com.mas.global.calculatesalary.api.dto.response.EmployeeResponse;
import com.mas.global.calculatesalary.facade.EmployeeFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruben Triana
 *
 * This controller allows you to manage all the information related to employees
 */
@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    private EmployeeFacade facade;

    public EmployeeController(EmployeeFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create a new Employee", summary = "It is allowed to create a new employee.")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employee) {
        return ResponseEntity.ok(facade.createEmployee(employee));
    }

    @PutMapping(value = "/{identification}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Edit Employee", summary = "It is allowed to modify a employee.")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("identification") final long identification, @RequestBody EmployeeRequest employee) {
        return ResponseEntity.ok(facade.updateEmployee(identification, employee));
    }

    @GetMapping("/all")
    @Operation(description = "Find all Employees", summary = "This service get all Employees active.")
    public ResponseEntity<List<EmployeeResponse>> findAll() {

        return ResponseEntity.ok(facade.findAll());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedServerError(RuntimeException ex) {
        return ex.getMessage();
    }
}
