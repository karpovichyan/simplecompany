package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.entity.Position;
import by.intexsoft.testproject.simplecompany.exception.PositionNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.PositionMapper;
import by.intexsoft.testproject.simplecompany.repository.PositionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PositionServiceImplTest {

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private PositionMapper positionMapper;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Test
    @DisplayName("Should save new position and return new dto")
    void createSuccessfully() {
        PositionDto positionDto = new PositionDto(null, "Junior Developer", 300);
        Position positionFromDto = new Position(null, "Junior Developer", 300);
        Position positionFromRepository = new Position(1, "Junior Developer", 300);
        PositionDto positionDtoFromEntity = new PositionDto(1, "Junior Developer", 300);

        when(positionMapper.toEntity(positionDto)).thenReturn(positionFromDto);
        when(positionRepository.save(positionFromDto)).thenReturn(positionFromRepository);
        when(positionMapper.toDto(positionFromRepository)).thenReturn(positionDtoFromEntity);

        PositionDto newPositionDto = positionService.create(positionDto);

        assertThat(newPositionDto).isEqualTo(positionDtoFromEntity);
        assertThat(newPositionDto.getName()).isEqualTo(positionDtoFromEntity.getName());
        assertThat(newPositionDto.getSalary()).isEqualTo(positionDtoFromEntity.getSalary());

        verify(positionMapper).toEntity(positionDto);
        verify(positionRepository).save(positionFromDto);
        verify(positionMapper).toDto(positionFromRepository);

        verifyNoMoreInteractions(positionRepository, positionMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when positionDto == null")
    void createThrowExceptionWhenParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> positionService.create(null))
                .withMessage("positionDto is null");

        verifyNoInteractions(positionRepository, positionMapper);
    }

    @Test
    @DisplayName("Should return list of all position dto's")
    void getAllSuccessfully() {
        Position positionOne = new Position(1, "Junior Developer", 300);
        Position positionTwo = new Position(2, "Middle Developer", 500);

        List<Position> positionsFromRepository = Arrays.asList(positionOne, positionTwo);

        PositionDto positionDtoOne = new PositionDto(1, "Junior Developer", 300);
        PositionDto positionDtoTwo = new PositionDto(2, "Middle Developer", 500);

        when(positionRepository.findAll()).thenReturn(positionsFromRepository);
        when(positionMapper.toDto(positionOne)).thenReturn(positionDtoOne);
        when(positionMapper.toDto(positionTwo)).thenReturn(positionDtoTwo);

        List<PositionDto> newPositionDtos = positionService.getAll();

        assertThat(newPositionDtos).hasSize(2);
        assertThat(newPositionDtos).containsExactly(positionDtoOne, positionDtoTwo);
        assertThat(newPositionDtos)
                .extracting(PositionDto::getName)
                .containsExactly("Junior Developer", "Middle Developer");

        assertThat(newPositionDtos)
                .extracting(PositionDto::getSalary)
                .containsExactly(300, 500);

        verify(positionRepository).findAll();
        verify(positionMapper).toDto(positionOne);
        verify(positionMapper).toDto(positionTwo);

        verifyNoMoreInteractions(positionRepository, positionMapper);
    }

    @Test
    @DisplayName("Should return empty list")
    void getAllSuccessfullyReturnEmptyList() {
        List<Position> emptyPositions = Collections.emptyList();

        when(positionRepository.findAll()).thenReturn(emptyPositions);

        List<PositionDto> emptyPositionDtos = positionService.getAll();

        assertThat(emptyPositionDtos).isEmpty();

        verify(positionRepository).findAll();

        verifyNoMoreInteractions(positionRepository);
        verifyNoInteractions(positionMapper);
    }

    @Test
    @DisplayName("Should update position and return new position dto")
    void updateSuccessfully() {
        PositionDto positionDto = new PositionDto(null, "Junior Developer", 300);
        Integer positionId = 1;

        Position positionFromRepository = new Position(1, "Middle Developer", 500);
        PositionDto positionDtoFromEntity = new PositionDto(1, "Middle Developer", 500);

        when(positionRepository.findById(1)).thenReturn(Optional.of(positionFromRepository));
        doNothing().when(positionMapper).updateFromDto(positionDto, positionFromRepository);
        when(positionMapper.toDto(positionFromRepository)).thenReturn(positionDtoFromEntity);

        PositionDto newPositionDto = positionService.update(positionDto, positionId);

        assertThat(newPositionDto).isEqualTo(positionDtoFromEntity);
        assertThat(newPositionDto.getName()).isEqualTo(positionDtoFromEntity.getName());
        assertThat(newPositionDto.getSalary()).isEqualTo(positionDtoFromEntity.getSalary());

        verify(positionRepository).findById(positionId);
        verify(positionMapper).updateFromDto(positionDto, positionFromRepository);
        verify(positionMapper).toDto(positionFromRepository);

        verifyNoMoreInteractions(positionRepository, positionMapper);
    }

    @Test
    @DisplayName("Should throw PositionNotFoundException when optional is empty")
    void updateThrowPositionNotFoundException() {
        PositionDto positionDto = new PositionDto(null, "Junior Developer", 300);
        Integer positionId = 1;

        when(positionRepository.findById(positionId)).thenReturn(Optional.empty());

        assertThatExceptionOfType(PositionNotFoundException.class)
                .isThrownBy(() -> positionService.update(positionDto, positionId))
                .withMessage("Position with id = " + positionId + " not found");

        verify(positionRepository).findById(positionId);

        verifyNoMoreInteractions(positionRepository);
        verifyNoInteractions(positionMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when positionDto == null")
    void updateThrowExceptionWhenDtoParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> positionService.update(null, 1))
                .withMessage("positionDto is null");

        verifyNoInteractions(positionRepository, positionMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when positionId == null")
    void updateThrowExceptionWhenIdParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> positionService.update(new PositionDto(), null))
                .withMessage("positionId is null");

        verifyNoInteractions(positionRepository, positionMapper);
    }
}