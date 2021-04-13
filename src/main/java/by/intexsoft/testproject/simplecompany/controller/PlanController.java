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
}
