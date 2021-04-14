
package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.PlanMapper;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    public PlanServiceImpl(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    @Override
    public void createPlan(PlanDto planDto) {
        planRepository.save(planMapper.planDtoToPlan(planDto));
    }

    @Override
    public List<PlanDto> getAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(planMapper::planToPlanDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDto getPlan(Integer planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan with id = " + planId + " not found"));
        return planMapper.planToPlanDto(plan);
    }

    @Override
    public void deletePlan(Integer planId) {
        planRepository.deleteById(planId);
    }

    @Override
    public void updatePlan(PlanDto planDto, Integer planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan with id = " + planId + " not found"));
        plan.setDate(planDto.getDate());
        plan.setTotalHours(planDto.getTotalHours());
        planRepository.save(plan);
    }
}