package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;

import java.util.List;
import java.util.Set;

public interface ContractService {
    ContractDto createContract(ContractDto contractDto);

    Set<ContractDto> getContractByIds(Integer contractId);

    ContractDto getContractInfo(Integer contractId);

    List<ContractDto> getAllContracts();
}
