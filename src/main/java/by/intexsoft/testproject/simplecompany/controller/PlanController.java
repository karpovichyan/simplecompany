package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.service.PlanService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public PlanDto create(@Valid @RequestBody PlanDto planDto) {
        return planService.create(planDto);
    }

    @GetMapping
    public List<PlanDto> getAll() {
        return planService.getAll();
    }

    @GetMapping("/{planId}")
    public PlanDto get(@PathVariable Integer planId) {
        return planService.get(planId);
    }

    @DeleteMapping("/{planId}")
    public void delete(@PathVariable Integer planId) {
        planService.delete(planId);
    }

    @PutMapping("/{planId}")
    public PlanDto update(@Valid @RequestBody PlanDto planDto, @PathVariable Integer planId) {
        return planService.update(planDto, planId);
    }
}
