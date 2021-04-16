package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public PositionDto create(@Valid @RequestBody PositionDto positionDto) {
        return positionService.create(positionDto);
    }

    @GetMapping
    public List<PositionDto> getAll() {
        return positionService.getAll();
    }

    @PutMapping("/{positionId}")
    public PositionDto update(@Valid @RequestBody PositionDto positionDto, @PathVariable Integer positionId) {
        return positionService.update(positionDto, positionId);
    }
}
