package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.properties.ConfigProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FilePayslipWriter implements PayslipWriter {
    private final ConfigProperties configProperties;
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    public FilePayslipWriter(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public void create(EmployeeActivity employeeActivity, String resultContent) throws IOException {
        String directoryPath = getPathDirectory(employeeActivity.getPlan().getDate());

        String payslipName = String.format("%s_%d_%s_%s.%s",
                configProperties.getFileNamePrefix(),
                employeeActivity.getEmployee().getId(),
                employeeActivity.getEmployee().getFirstName(),
                employeeActivity.getEmployee().getLastName(),
                configProperties.getFileExtension());

        String payslipDirectoryName = String.format("%s/%s",
                directoryPath, payslipName);

        Files.write(Paths.get(payslipDirectoryName), resultContent.getBytes(), StandardOpenOption.CREATE);
    }

    @Override
    public List<Employee> getExistPayslips(LocalDate date, Set<Employee> allEmployees) throws IOException {

        List<Employee> existingEmployeePayslips = new ArrayList<>();
        List<String> existingEmployeePayslipsIds = new ArrayList<>();

        Files.createDirectories(Paths.get(getPathDirectory(date)));
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(getPathDirectory(date)));
        for (Path path : directoryStream) {
            String idFromFileName = String.valueOf(Integer.parseInt(String.valueOf(path.getFileName())
                    .replaceAll("\\D", "")));
            existingEmployeePayslipsIds.add(idFromFileName);
        }

        for (Employee employee : allEmployees) {
            for (String str : existingEmployeePayslipsIds) {
                if (String.valueOf(employee.getId()).equals(str)) {
                    existingEmployeePayslips.add(employee);
                }
            }
        }
        return existingEmployeePayslips;
    }

    private String getPathDirectory(LocalDate date) {
        return String.format("%s/%s",
                configProperties.getPath(), date.format(MMMM_YYYY_FORMATTER));
    }
}
