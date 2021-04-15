package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.entity.Position;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.PositionMapper;
import by.intexsoft.testproject.simplecompany.repository.PositionRepository;
import by.intexsoft.testproject.simplecompany.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    public PositionServiceImpl(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public PositionDto create(PositionDto positionDto) {
        return positionMapper.toDto(positionRepository.save(positionMapper.toEntity(positionDto)));
    }

    @Override
    public List<PositionDto> getAll() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(PositionDto positionDto, Integer positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new PlanNotFoundException("Position with id = " + positionId + " not found"));
        position.setName(positionDto.getName());
        position.setSalary(positionDto.getSalary());
        positionRepository.save(position);
    }
}
