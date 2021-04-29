package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeMapper;
import by.intexsoft.testproject.simplecompany.mapper.context.CycleAvoidingMappingContext;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private PlanRepository planRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Should throw IllegalArgumentException when date == null")
    void getEmployeesByAbsencesThrowIllegalArgumentException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> employeeService.getEmployeesByAbsences(null))
                .withMessage("date is null");

        verifyNoInteractions(planRepository, employeeMapper);
    }

    @Test
    @DisplayName("Should throw PlanNotFoundException when optional is empty")
    void getEmployeesByAbsencesShouldThrowPlanNotFoundException() {
        LocalDate date = LocalDate.now();
        when(planRepository.findByDate(date)).thenReturn(Optional.empty());

        assertThatExceptionOfType(PlanNotFoundException.class)
                .isThrownBy(() -> employeeService.getEmployeesByAbsences(date))
                .withMessage("Plan with date " + date + " not found");

        verify(planRepository).findByDate(date);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(employeeMapper);
    }

    @Test
    @DisplayName("Should return empty list when employee has no absence")
    void getEmployeesByAbsenceShouldReturnEmptyList() {
        LocalDate date = LocalDate.now();

        Position position = new Position(1, "Junior Developer", 300);

        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));

        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        Activity activity = new Activity(1, PRESENT, BigDecimal.valueOf(1));

        HashSet<EmployeeActivity> employeeActivities = new HashSet<>();

        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivityElem = new EmployeeActivity(1, 100, employee, plan, activity);

        employeeActivities.add(employeeActivityElem);
        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));

        List<EmployeeDto> employeesByAbsences = employeeService.getEmployeesByAbsences(date);

        assertThat(employeesByAbsences).hasSize(0);

        verify(planRepository).findByDate(date);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(employeeMapper);
    }

    @Test
    @DisplayName("Should return empty list when employee has only reason absence")
    void getEmployeeByAbsenceShouldReturnEmptyListWhenOnlyReasonAbsence() {
        LocalDate date = LocalDate.now();
        Activity activity1 = new Activity(1, VACATION, BigDecimal.valueOf(0.8));
        Activity activity2 = new Activity(1, SICK_LEAVE, BigDecimal.valueOf(0.6));
        Position position = new Position(1, "Java Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));

        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        HashSet<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivity1 = new EmployeeActivity(1, 40, employee, plan, activity1);
        EmployeeActivity employeeActivity2 = new EmployeeActivity(2, 60, employee, plan, activity2);

        employeeActivities.add(employeeActivity1);
        employeeActivities.add(employeeActivity2);

        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));

        List<EmployeeDto> employeesByAbsences = employeeService.getEmployeesByAbsences(date);

        assertThat(employeesByAbsences).hasSize(0);

        verify(planRepository).findByDate(date);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(employeeMapper);
    }

    @Test
    @DisplayName("Should return list of employeeDto when employee has more than 50% absence without reason")
    void getEmployeeByAbsenceShouldReturnListOfDtos() {
        LocalDate date = LocalDate.now();

        Position position = new Position(1, "Junior Developer", 300);

        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));

        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        Activity activity = new Activity(1, PRESENT, BigDecimal.valueOf(1));

        Set<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivityElem = new EmployeeActivity(1, 50, employee, plan, activity);
        employeeActivities.add(employeeActivityElem);

        PositionDto positionDto = new PositionDto(1, "Junior Developer", 300);
        ContractDto contractDto = new ContractDto(1, LocalDate.parse("2021-01-01"));
        EmployeeDto employeeDto = new EmployeeDto(1, "firstName", "lastName", positionDto, contractDto);

        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));
        when(employeeMapper.toDto(eq(employee), any(CycleAvoidingMappingContext.class))).thenReturn(employeeDto);

        List<EmployeeDto> employeesByAbsences = employeeService.getEmployeesByAbsences(date);

        assertThat(employeesByAbsences).contains(employeeDto);
        assertThat(employeesByAbsences.get(0).getContract()).isEqualTo(contractDto);
        assertThat(employeesByAbsences.get(0).getPosition()).isEqualTo(positionDto);
        assertThat(employeesByAbsences.get(0).getFirstName()).isEqualTo(employeeDto.getFirstName());
        assertThat(employeesByAbsences.get(0).getLastName()).isEqualTo(employeeDto.getLastName());

        verify(planRepository).findByDate(date);
        verify(employeeMapper).toDto(eq(employee), any(CycleAvoidingMappingContext.class));

        verifyNoMoreInteractions(planRepository, employeeMapper);
    }

    @Test
    @DisplayName("Should return list of employeeDto when employee has more than 50% absence without reason and some absence with reason")
    void getEmployeeByAbsenceShouldReturnListOfDtosWhenHasReasonAbsence() {
        LocalDate date = LocalDate.now();
        Activity activity1 = new Activity(1, PRESENT, BigDecimal.valueOf(1.0));
        Activity activity2 = new Activity(1, VACATION, BigDecimal.valueOf(0.8));
        Activity activity3 = new Activity(1, SICK_LEAVE, BigDecimal.valueOf(0.6));
        Position position = new Position(1, "Java Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));

        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        HashSet<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivity1 = new EmployeeActivity(1, 20, employee, plan, activity1);
        EmployeeActivity employeeActivity2 = new EmployeeActivity(2, 20, employee, plan, activity2);
        EmployeeActivity employeeActivity3 = new EmployeeActivity(3, 20, employee, plan, activity3);

        employeeActivities.add(employeeActivity1);
        employeeActivities.add(employeeActivity2);
        employeeActivities.add(employeeActivity3);

        PositionDto positionDto = new PositionDto(1, "Junior Developer", 300);
        ContractDto contractDto = new ContractDto(1, LocalDate.parse("2021-01-01"));
        EmployeeDto employeeDto = new EmployeeDto(1, "firstName", "lastName", positionDto, contractDto);

        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));
        when(employeeMapper.toDto(eq(employee), any(CycleAvoidingMappingContext.class))).thenReturn(employeeDto);

        List<EmployeeDto> employeesByAbsences = employeeService.getEmployeesByAbsences(date);

        assertThat(employeesByAbsences).contains(employeeDto);

        verify(planRepository).findByDate(date);
        verify(employeeMapper).toDto(eq(employee), any(CycleAvoidingMappingContext.class));

        verifyNoMoreInteractions(planRepository, employeeMapper);
    }
}
