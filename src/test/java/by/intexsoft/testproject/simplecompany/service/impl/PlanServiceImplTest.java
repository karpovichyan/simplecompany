package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.PlanMapper;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

    @Mock
    private PlanRepository planRepository;
    @Mock
    private PlanMapper planMapper;

    @InjectMocks
    private PlanServiceImpl planService;

    @Test
    @DisplayName("Should save new plan and return new dto")
    void createSuccessfully() {
        PlanDto planDto = new PlanDto(null, LocalDate.parse("2021-01-01"), 100);
        Plan planFromDto = new Plan(null, LocalDate.parse("2021-01-01"), 100);
        Plan planFromRepository = new Plan(1, LocalDate.parse("2021-01-01"), 100);
        PlanDto planDtoFromEntity = new PlanDto(1, LocalDate.parse("2021-01-01"), 100);

        when(planMapper.toEntity(planDto)).thenReturn(planFromDto);
        when(planRepository.save(planFromDto)).thenReturn(planFromRepository);
        when(planMapper.toDto(planFromRepository)).thenReturn(planDtoFromEntity);

        PlanDto newPlanDto = planService.create(planDto);

        assertThat(newPlanDto).isEqualTo(planDtoFromEntity);
        assertThat(newPlanDto.getDate()).isEqualTo(planDtoFromEntity.getDate());
        assertThat(newPlanDto.getTotalHours()).isEqualTo(planDtoFromEntity.getTotalHours());

        verify(planMapper).toEntity(planDto);
        verify(planRepository).save(planFromDto);
        verify(planMapper).toDto(planFromRepository);

        verifyNoMoreInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when planDto == null")
    void createThrowExceptionWhenParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> planService.create(null))
                .withMessage("planDto is null");

        verifyNoInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should return list of all plan dto's")
    void getAllSuccessfully() {
        Plan planOne = new Plan(1, LocalDate.parse("2021-01-01"), 100);
        Plan planTwo = new Plan(2, LocalDate.parse("2021-02-02"), 150);

        List<Plan> plansFromRepository = Arrays.asList(planOne, planTwo);

        PlanDto planDtoOne = new PlanDto(1, LocalDate.parse("2021-01-01"), 100);
        PlanDto planDtoTwo = new PlanDto(2, LocalDate.parse("2021-02-02"), 150);

        when(planRepository.findAll()).thenReturn(plansFromRepository);
        when(planMapper.toDto(planOne)).thenReturn(planDtoOne);
        when(planMapper.toDto(planTwo)).thenReturn(planDtoTwo);

        List<PlanDto> newPlanDtos = planService.getAll();

        assertThat(newPlanDtos).hasSize(2);
        assertThat(newPlanDtos).containsExactly(planDtoOne, planDtoTwo);
        assertThat(newPlanDtos)
                .extracting(PlanDto::getDate)
                .containsExactly(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-02-02"));

        assertThat(newPlanDtos)
                .extracting(PlanDto::getTotalHours)
                .containsExactly(100, 150);

        verify(planRepository).findAll();
        verify(planMapper).toDto(planOne);
        verify(planMapper).toDto(planTwo);

        verifyNoMoreInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should return empty list")
    void getAllSuccessfullyReturnEmptyList() {
        List<Plan> emptyPlans = Collections.emptyList();

        when(planRepository.findAll()).thenReturn(emptyPlans);

        List<PlanDto> emptyPlanDtos = planService.getAll();

        assertThat(emptyPlanDtos).isEmpty();

        verify(planRepository).findAll();

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(planMapper);
    }

    @Test
    @DisplayName("should get plan and return dto")
    void getOnePlanSuccessfully() {
        Integer planId = 1;

        Plan planFromRepository = new Plan(1, LocalDate.parse("2021-02-02"), 150);
        PlanDto planDtoFromEntity = new PlanDto(1, LocalDate.parse("2021-01-01"), 150);

        when(planRepository.findById(planId)).thenReturn(Optional.of(planFromRepository));
        when(planMapper.toDto(planFromRepository)).thenReturn(planDtoFromEntity);

        PlanDto newPlanDto = planService.get(planId);

        assertThat(newPlanDto).isEqualTo(planDtoFromEntity);
        assertThat(newPlanDto.getDate()).isEqualTo(planDtoFromEntity.getDate());
        assertThat(newPlanDto.getTotalHours()).isEqualTo(planDtoFromEntity.getTotalHours());

        verifyNoMoreInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should throw PlanNotFoundException when optional is empty")
    void getThrowPlanNotFoundException() {
        Integer planId = 1;

        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        assertThatExceptionOfType(PlanNotFoundException.class)
                .isThrownBy(() -> planService.get(planId))
                .withMessage("Plan with id = " + planId + " not found");

        verify(planRepository).findById(planId);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(planMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when planId == null")
    void getThrowExceptionWhenIdParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> planService.get(null));

        verifyNoInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should delete plan")
    void deleteSuccessfully() {
        Integer planId = 1;

        doNothing().when(planRepository).deleteById(planId);

        planService.delete(planId);

        verify(planRepository).deleteById(1);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(planMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when planId == null")
    void deleteThrowExceptionWhenIdParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> planService.delete(null))
                .withMessage("planId is null");

        verifyNoInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should update plan and return new plan dto")
    void updateSuccessfully() {
        PlanDto planDto = new PlanDto(null, LocalDate.parse("2021-01-01"), 100);
        Integer planId = 1;

        Plan planFromRepository = new Plan(1, LocalDate.parse("2021-02-02"), 150);
        PlanDto planDtoFromEntity = new PlanDto(1, LocalDate.parse("2021-01-01"), 150);

        when(planRepository.findById(planId)).thenReturn(Optional.of(planFromRepository));
        doNothing().when(planMapper).updateFromDto(planDto, planFromRepository);
        when(planMapper.toDto(planFromRepository)).thenReturn(planDtoFromEntity);

        PlanDto newPlanDto = planService.update(planDto, planId);

        assertThat(newPlanDto).isEqualTo(planDtoFromEntity);
        assertThat(newPlanDto.getDate()).isEqualTo(planDtoFromEntity.getDate());
        assertThat(newPlanDto.getTotalHours()).isEqualTo(planDtoFromEntity.getTotalHours());

        verify(planRepository).findById(planId);
        verify(planMapper).updateFromDto(planDto, planFromRepository);
        verify(planMapper).toDto(planFromRepository);

        verifyNoMoreInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should throw PlanNotFoundException when optional is empty")
    void updateThrowPlanNotFoundException() {
        PlanDto planDto = new PlanDto(null, LocalDate.parse("2021-01-01"), 100);
        Integer planId = 1;

        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        assertThatExceptionOfType(PlanNotFoundException.class)
                .isThrownBy(() -> planService.update(planDto, planId))
                .withMessage("Plan with id = " + planId + " not found");

        verify(planRepository).findById(planId);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(planMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when planDto == null")
    void updateThrowExceptionWhenDtoParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> planService.update(null, 1))
                .withMessage("planDto is null");

        verifyNoInteractions(planRepository, planMapper);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when planId == null")
    void updateThrowExceptionWhenIdParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> planService.update(new PlanDto(), null))
                .withMessage("planId is null");

        verifyNoInteractions(planRepository, planMapper);
    }
}