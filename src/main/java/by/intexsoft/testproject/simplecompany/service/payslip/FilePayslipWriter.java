package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.properties.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

@Service
public class FilePayslipWriter implements PayslipWriter {
    Logger log = LoggerFactory.getLogger(FilePayslipWriter.class);
    private final ConfigProperties configProperties;
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    public FilePayslipWriter(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public void create(EmployeeActivity employeeActivity, String resultContent) {
        String pathDirectory = configProperties.getPath() + "/" + employeeActivity.getPlan().getDate().format(MMMM_YYYY_FORMATTER);
        try {
            Files.createDirectories(Paths.get(pathDirectory));
            Files.write(Paths.get(pathDirectory + "/"
                            + configProperties.getFileNamePrefix() + "_"
                            + employeeActivity.getEmployee().getFirstName() + "_"
                            + employeeActivity.getEmployee().getLastName()
                            + "." + configProperties.getFileExtension()),
                    resultContent.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            log.warn("Payslip is not created", e);
        }
    }
}
