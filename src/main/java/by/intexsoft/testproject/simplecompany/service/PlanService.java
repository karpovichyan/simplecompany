package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;

import java.util.List;

public interface PlanService {
    PlanDto create(PlanDto planDto);

    List<PlanDto> getAll();

    void delete(Integer planId);

    PlanDto get(Integer planId);

    void update(PlanDto planDto, Integer planId);
}
