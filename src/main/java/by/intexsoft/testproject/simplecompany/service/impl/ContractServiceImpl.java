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
    public ContractDto create(ContractDto contractDto) {
        Contract contract = contractMapper.toEntity(contractDto);
        return contractMapper.toDto(contractRepository.save(contract));
    }

    @Override
    public Set<ContractDto> getByIds(List<Integer> contractId) {
        return contractRepository.findContractByIdIn(contractId)
                .stream()
                .map(contractMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public List<ContractDto> getAll() {
        return contractRepository.findAll()
                .stream()
                .map(contractMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto get(Integer contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException("Contract with id = " + contractId + " not found"));
        return contractMapper.toDto(contract);
    }

    @Override
    public void delete(Integer contractId) {
        contractRepository.deleteById(contractId);
    }

    @Override
    public void update(ContractDto contractDto, Integer contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException("Contract with id = " + contractId + " not found"));
        contract.setDate(contractDto.getDate());
        contractRepository.save(contract);
    }
}

