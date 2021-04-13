package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;

import java.util.List;

public interface PlanService {
    void createPlan(PlanDto planDto);

    List<PlanDto> getAllPlans();
}
