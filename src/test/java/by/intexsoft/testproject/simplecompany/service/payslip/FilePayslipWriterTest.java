package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.properties.ConfigProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.PRESENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilePayslipWriterTest {

    @TempDir
    File file;

    @Mock
    private ConfigProperties configProperties;

    @InjectMocks
    private FilePayslipWriter payslipWriter;

    @Test
    @DisplayName("Should successfully create a payslip")
    void createSuccess() throws IOException {
        LocalDate date = LocalDate.now();
        Activity activity = new Activity(1, PRESENT, BigDecimal.valueOf(1.0));

        Position position = new Position(1, "Java Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));

        Employee employee = new Employee(1, "firstName", "lastName", position, contract);
        Set<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivity = new EmployeeActivity(1, 20, employee, plan, activity);
        String resultContent = "Test";

        Files.createDirectories(Paths.get(file + "/" + date.format(DateTimeFormatter.ofPattern("MMMM yyyy"))));

        when(configProperties.getPath()).thenReturn(String.valueOf(file));
        when(configProperties.getFileNamePrefix()).thenReturn("payslip");
        when(configProperties.getFileExtension()).thenReturn("txt");

        assertAll(() -> payslipWriter.create(employeeActivity, resultContent));
        assertTrue(this.file.isDirectory(), "Should be a directory ");

        verify(configProperties).getPath();
        verify(configProperties).getFileNamePrefix();
        verify(configProperties).getFileExtension();

        verifyNoMoreInteractions(configProperties);
    }

    @Test
    @DisplayName("Should return list of employee id's for whom payslips have been created")
    void getExistPayslipsShouldReturnListOfEmployeeIds() throws IOException {
        LocalDate date = LocalDate.now();

        List<String> existingEmployeePayslipIds = new ArrayList<>();
        existingEmployeePayslipIds.add("1");

        String tempPath = file + "/" + date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        Files.createDirectories(Paths.get(tempPath));
        Files.write(Paths.get(tempPath + "/" + "1.txt"), "test".getBytes(), StandardOpenOption.CREATE);
        Files.write(Paths.get(tempPath + "/" + "q.txt"), "test".getBytes(), StandardOpenOption.CREATE);
        when(configProperties.getPath()).thenReturn(String.valueOf(file));

        List<String> newExistingEmployeePayslipIds = payslipWriter.getExistPayslipIds(date);

        assertThat(newExistingEmployeePayslipIds).hasSize(1);
        assertThat(newExistingEmployeePayslipIds.get(0)).isEqualTo(existingEmployeePayslipIds.get(0));

        verifyNoMoreInteractions(configProperties);
    }

    @Test
    @DisplayName("Should return empty list when directory with payslips is empty")
    void getExistPayslipsShouldReturnEmptyList() throws IOException {
        LocalDate date = LocalDate.now();

        String tempPath = file + "/" + date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        Files.createDirectories(Paths.get(tempPath));
        when(configProperties.getPath()).thenReturn(String.valueOf(file));

        List<String> newExistingEmployeePayslipIds = payslipWriter.getExistPayslipIds(date);

        assertThat(newExistingEmployeePayslipIds).hasSize(0);

        verify(configProperties).getPath();

        verifyNoMoreInteractions(configProperties);
    }

    @Test
    @DisplayName("Should create directory")
    void createDirectorySuccess() throws IOException {
        LocalDate date = LocalDate.now();

        Files.createDirectories(Paths.get(file.toString()));
        when(configProperties.getPath()).thenReturn(String.valueOf(file));

        payslipWriter.createDirectory(date);

        verify(configProperties).getPath();

        verifyNoMoreInteractions(configProperties);
    }
}