package com.mas.global.calculatesalary.api.controller;


import com.mas.global.calculatesalary.api.dto.request.ContractRequest;
import com.mas.global.calculatesalary.api.dto.response.ContractResponse;
import com.mas.global.calculatesalary.facade.ContractFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruben Triana
 *
 * This controller allows you to manage all the information related to contracts
 */
@RestController
@RequestMapping(path = "/api/contract")
public class ContractController {

    private final ContractFacade facade;

    public ContractController(ContractFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create a new Contract", summary = "It is allowed to create a new contract associated with an employee.")
    public ResponseEntity<ContractResponse> createContract(@RequestBody ContractRequest request) {
        return ResponseEntity.ok(facade.createContract(request));
    }

    @PutMapping(value = "/{contractNumber}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Edit Contract", summary = "It is allowed to modify a contract.")
    public ResponseEntity<ContractResponse> updateContract(@PathVariable("contractNumber") final long contractNumber, @RequestBody ContractRequest request) {
        return ResponseEntity.ok(facade.updateContract(contractNumber, request));
    }

    @GetMapping("/all")
    @Operation(description = "Find all Contracts", summary = "This service get all Contracts active.")
    public ResponseEntity<List<ContractResponse>> findAllActive() {
        return ResponseEntity.ok(facade.findAll());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedServerError(RuntimeException ex) {
        return ex.getMessage();
    }
}
