package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.properties.ConfigProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FilePayslipWriter implements PayslipWriter {
    private final ConfigProperties configProperties;
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");
    private static final Pattern pattern = Pattern.compile("\\d+");

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
    public List<String> getExistPayslipIds(LocalDate date) throws IOException {

        List<String> existingEmployeePayslipsIds = new ArrayList<>();

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(getPathDirectory(date)));
        for (Path file : directoryStream) {
            if (!file.toFile().isDirectory()) {
                Matcher matcher = pattern.matcher(file.getFileName().toString());
                while (matcher.find()) {
                    existingEmployeePayslipsIds.add(matcher.group());
                }
            }
        }
        return existingEmployeePayslipsIds;
    }

    @Override
    public void createDirectory(LocalDate date) throws IOException {
        Files.createDirectories(Paths.get(getPathDirectory(date)));
    }

    private String getPathDirectory(LocalDate date) {
        return String.format("%s/%s",
                configProperties.getPath(), date.format(MMMM_YYYY_FORMATTER));
    }
}
