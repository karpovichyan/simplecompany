package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;

import java.util.List;

public interface PositionService {
    void createPosition(PositionDto positionDto);

    List<PositionDto> getAllPositions();

    void updatePosition(PositionDto positionDto, Integer positionId);
}
