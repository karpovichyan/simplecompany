package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;

import java.util.List;
import java.util.Set;

public interface ContractService {
    ContractDto create(ContractDto contractDto);

    Set<ContractDto> getByIds(List<Integer> contractId);

    ContractDto get(Integer contractId);

    List<ContractDto> getAll();

    void delete(Integer contractId);

    void update(ContractDto contractDto, Integer contractId);
}
