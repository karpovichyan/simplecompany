package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
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
    public void createPosition(PositionDto positionDto) {
        positionRepository.save(positionMapper.positionDtoToPosition(positionDto));
    }

    @Override
    public List<PositionDto> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::positionToPositionDto)
                .collect(Collectors.toList());
    }
}
