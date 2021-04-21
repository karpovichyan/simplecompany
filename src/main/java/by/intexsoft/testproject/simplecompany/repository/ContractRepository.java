package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Set<Contract> findContractByIdIn(List<Integer> ids);
}
