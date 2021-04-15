package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;

import java.util.List;

public interface PositionService {
    PositionDto create(PositionDto positionDto);

    List<PositionDto> getAll();

    void update(PositionDto positionDto, Integer positionId);
}
