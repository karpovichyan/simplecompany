
package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.PlanMapper;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PlanDto create(PlanDto planDto) {
        if (planDto == null) {
            throw new IllegalArgumentException("planDto should not be null");
        }
        Plan entity = planMapper.toEntity(planDto);
        return planMapper.toDto(planRepository.save(entity));
    }

    @Override
    public List<PlanDto> getAll() {
        return planRepository.findAll()
                .stream()
                .map(planMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDto get(Integer planId) {
        if (planId == null) {
            throw new IllegalArgumentException("planId should not be null");
        }
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan with id = " + planId + " not found"));
        return planMapper.toDto(plan);
    }

    @Override
    public void delete(Integer planId) {
        if (planId == null) {
            throw new IllegalArgumentException("planId should not be null");
        }
        planRepository.deleteById(planId);
    }

    @Override
    @Transactional
    public PlanDto update(PlanDto planDto, Integer planId) {
        if (planDto == null) {
            throw new IllegalArgumentException("planDto should not be null");
        }
        if (planId == null) {
            throw new IllegalArgumentException("planId should not be null");
        }
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan with id = " + planId + " not found"));
        planMapper.updateFromDto(planDto, plan);
        return planMapper.toDto(plan);
    }
}