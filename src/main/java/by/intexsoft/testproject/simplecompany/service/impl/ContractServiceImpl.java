package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.exception.ContractNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.ContractMapper;
import by.intexsoft.testproject.simplecompany.repository.ContractRepository;
import by.intexsoft.testproject.simplecompany.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    public ContractServiceImpl(ContractRepository contractRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    @Override
    public ContractDto createContract(ContractDto contractDto) {
        Contract contract = contractMapper.contractDtoToContract(contractDto);
        return contractMapper.contractToContractDto(contractRepository.save(contract));
    }

    @Override
    public Set<ContractDto> getContractByIds(List<Integer> contractId) {
        return contractRepository.findContractByIdIn(contractId)
                .stream()
                .map(contractMapper::contractToContractDto)
                .collect(Collectors.toSet());
    }

    @Override
    public List<ContractDto> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(contractMapper::contractToContractDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto getContract(Integer contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException("Contract with id = " + contractId + " not found"));
        return contractMapper.contractToContractDto(contract);
    }

    @Override
    public void deleteContract(Integer contractId) {
        contractRepository.deleteById(contractId);
    }

    @Override
    public void updateContract(ContractDto contractDto, Integer contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException("Contract with id = " + contractId + " not found"));
        contract.setDate(contractDto.getDate());
        contractRepository.save(contract);
    }
}

