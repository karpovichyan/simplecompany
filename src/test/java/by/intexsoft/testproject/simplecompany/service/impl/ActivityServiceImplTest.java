package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.entity.Activity;
import by.intexsoft.testproject.simplecompany.mapper.ActivityMapper;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.SICK_LEAVE;
import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.VACATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private ActivityMapper activityMapper;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    @DisplayName("Should save new activity and return new Dto")
    void createSuccessfully() {
        ActivityDto activityDto = new ActivityDto(null, SICK_LEAVE, new BigDecimal("0.6"));
        Activity activityFromDto = new Activity(null, SICK_LEAVE, new BigDecimal("0.6"));
        Activity activityFromRepository = new Activity(1, SICK_LEAVE, new BigDecimal("0.6"));
        ActivityDto activityDtoFromEntity = new ActivityDto(1, SICK_LEAVE, new BigDecimal("0.6"));

        when(activityMapper.toEntity(activityDto)).thenReturn(activityFromDto);
        when(activityRepository.save(activityFromDto)).thenReturn(activityFromRepository);
        when(activityMapper.toDto(activityFromRepository)).thenReturn(activityDtoFromEntity);

        ActivityDto newActivityDto = activityService.create(activityDto);

        assertThat(newActivityDto).isEqualTo(activityDtoFromEntity);
        assertThat(newActivityDto.getId()).isEqualTo(activityDtoFromEntity.getId());
        assertThat(newActivityDto.getActivityType()).isEqualTo(activityDtoFromEntity.getActivityType());
        assertThat(newActivityDto.getRatio()).isEqualTo(activityDtoFromEntity.getRatio());

        verify(activityMapper).toEntity(activityDto);
        verify(activityRepository).save(activityFromDto);
        verify(activityMapper).toDto(activityFromRepository);

        verifyNoMoreInteractions(activityRepository, activityMapper);
    }

    @Test
    @DisplayName("Should throw NullPointerException when activityDto == null")
    void createThrowExceptionWhenParamEqualsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> activityService.create(null))
                .withMessage("activityDto is null");

        verifyNoInteractions(activityRepository, activityMapper);
    }

    @Test
    @DisplayName("Should return list of dto's")
    void getAllSuccessfully() {
        Activity activityOne = new Activity(1, SICK_LEAVE, new BigDecimal("0.6"));
        Activity activityTwo = new Activity(2, VACATION, new BigDecimal("0.8"));
        List<Activity> activitiesFromRepository = Arrays.asList(activityOne, activityTwo);

        ActivityDto activityDtoOne = new ActivityDto(1, SICK_LEAVE, new BigDecimal("0.6"));
        ActivityDto activityDtoTwo = new ActivityDto(2, VACATION, new BigDecimal("0.8"));
        List<ActivityDto> activityDtosFromEntity = Arrays.asList(activityDtoOne, activityDtoTwo);

        when(activityRepository.findAll()).thenReturn(activitiesFromRepository);
        when(activityMapper.toDto(activityOne)).thenReturn(activityDtoOne);
        when(activityMapper.toDto(activityTwo)).thenReturn(activityDtoTwo);

        List<ActivityDto> newActivityDtos = activityService.getAll();

        assertThat(newActivityDtos).isEqualTo(activityDtosFromEntity);

        assertThat(newActivityDtos).hasSize(2);
        assertThat(newActivityDtos).containsExactly(activityDtoOne, activityDtoTwo);
        assertThat(newActivityDtos)
                .extracting(ActivityDto::getActivityType)
                .containsExactly(SICK_LEAVE, VACATION);
        assertThat(newActivityDtos)
                .extracting(ActivityDto::getRatio)
                .containsExactly(new BigDecimal("0.6"), new BigDecimal("0.8"));

        verify(activityRepository).findAll();
        verify(activityMapper).toDto(activityOne);
        verify(activityMapper).toDto(activityTwo);

        verifyNoMoreInteractions(activityRepository, activityMapper);
    }

    @Test
    @DisplayName("Should return empty list")
    void getAllSuccessfullyReturnEmptyList() {
        List<Activity> activitiesFromRepository = Collections.emptyList();

        when(activityRepository.findAll()).thenReturn(activitiesFromRepository);

        List<ActivityDto> newActivityDtos = activityService.getAll();

        assertThat(newActivityDtos).isEmpty();

        verify(activityRepository).findAll();

        verifyNoMoreInteractions(activityRepository);
        verifyNoInteractions(activityMapper);
    }
}