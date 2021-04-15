package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDto create(@RequestBody ContractDto contractDto) {
        return contractService.create(contractDto);
    }

    @GetMapping
    public List<ContractDto> getAll() {
        return contractService.getAll();
    }

    @GetMapping("/ids")
    public Set<ContractDto> getByIds(@RequestParam List<Integer> contractIds) {
        return contractService.getByIds(contractIds);
    }

    @GetMapping(path = "/{contractId}")
    public ContractDto get(@PathVariable Integer contractId) {
        return contractService.get(contractId);
    }

    @DeleteMapping("/{contractId}")
    public void delete(@PathVariable Integer contractId) {
        contractService.delete(contractId);
    }

    @PutMapping("/{contractId}")
    public void update(@RequestBody ContractDto contractDto, @PathVariable Integer contractId) {
        contractService.update(contractDto, contractId);
    }
}
