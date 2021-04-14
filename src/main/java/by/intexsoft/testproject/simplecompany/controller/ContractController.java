package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDto createContract(@RequestBody ContractDto contractDto) {
        return contractService.createContract(contractDto);
    }

    @GetMapping
    public List<ContractDto> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/ids")
    public Set<ContractDto> getContractByIds(@RequestParam List<Integer> contractId) {
        return contractService.getContractByIds(contractId);
    }

    @GetMapping(path = "/{contractId}")
    public ContractDto getContract(@PathVariable Integer contractId) {
        return contractService.getContract(contractId);
    }

    @DeleteMapping("/{contractId}")
    public void deleteContract(@PathVariable Integer contractId) {
        contractService.deleteContract(contractId);
    }

    @PutMapping("/{contractId}")
    public void updateContract(@RequestBody ContractDto contractDto, @PathVariable Integer contractId) {
        contractService.updateContract(contractDto, contractId);
    }
}
