package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public void createPosition(@RequestBody PositionDto positionDto) {
        positionService.createPosition(positionDto);
    }

    @GetMapping
    public List<PositionDto> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PutMapping("/{positionId}")
    public void updatePosition(@RequestBody PositionDto positionDto, @PathVariable Integer positionId) {
        positionService.updatePosition(positionDto, positionId);
    }
}
