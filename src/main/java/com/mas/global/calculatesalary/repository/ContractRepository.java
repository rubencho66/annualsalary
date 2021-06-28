package com.mas.global.calculatesalary.repository;

import com.mas.global.calculatesalary.repository.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findByContractNumber(long contractNumber);
}
