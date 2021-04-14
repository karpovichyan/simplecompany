package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public void createPlan(@RequestBody PlanDto planDto) {
        planService.createPlan(planDto);
    }

    @GetMapping
    public List<PlanDto> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{planId}")
    public PlanDto getPlan(@PathVariable Integer planId) {
        return planService.getPlan(planId);
    }

    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Integer planId) {
        planService.deletePlan(planId);
    }

    @PutMapping("/{planId}")
    public void updatePlan(@RequestBody PlanDto planDto, @PathVariable Integer planId) {
        planService.updatePlan(planDto, planId);
    }
}
