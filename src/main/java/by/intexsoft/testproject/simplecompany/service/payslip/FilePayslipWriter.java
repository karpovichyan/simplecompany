package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.properties.ConfigProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

@Service
public class FilePayslipWriter implements PayslipWriter {
    private final ConfigProperties configProperties;
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    public FilePayslipWriter(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public void makePayslip(EmployeeActivity employeeActivity, String resultContent) throws IOException {
        String pathDirectory = configProperties.getPath() + "/" + employeeActivity.getPlan().getDate().format(MMMM_YYYY_FORMATTER);
        Files.createDirectories(Paths.get(pathDirectory));
        Files.write(Paths.get(pathDirectory + "/"
                        + configProperties.getFileNamePrefix() + "_"
                        + employeeActivity.getEmployee().getFirstName() + "_"
                        + employeeActivity.getEmployee().getLastName()
                        + "." + configProperties.getFileExtension()),
                resultContent.getBytes(), StandardOpenOption.CREATE);
    }
}
