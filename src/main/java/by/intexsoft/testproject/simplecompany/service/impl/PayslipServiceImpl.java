package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.DirectoryException;
import by.intexsoft.testproject.simplecompany.exception.PayslipNotCreatedException;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.PayslipService;
import by.intexsoft.testproject.simplecompany.service.details.PayslipsGenerationProgress;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipWriter;
import by.intexsoft.testproject.simplecompany.service.payslip.execute.PayslipGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PayslipServiceImpl implements PayslipService {
    private final Logger log = LoggerFactory.getLogger(PayslipServiceImpl.class);
    private final HashMap<LocalDate, PayslipsGenerationProgress> storage = new HashMap<>();
    private final PayslipGenerator payslipGenerator;
    private final PlanRepository planRepository;
    private final PayslipWriter payslipWriter;

    public PayslipServiceImpl(PlanRepository planRepository, PayslipGenerator payslipGenerator, PayslipWriter payslipWriter) {
        this.planRepository = planRepository;
        this.payslipGenerator = payslipGenerator;
        this.payslipWriter = payslipWriter;
    }

    @Override
    public void create(LocalDate date) {

        List<String> existEmployeesPayslipIds;
        try {
            payslipWriter.createDirectory(date);
            existEmployeesPayslipIds = payslipWriter.getExistPayslipIds(date);
        } catch (IOException exception) {
            throw new DirectoryException("Fail to create directory:", exception);
        }

        Plan plan = planRepository.findByDate(date)
                .orElseThrow(() -> new PlanNotFoundException("Plan with date " + date + " not found!"));

        Set<EmployeeActivity> employeeActivities = plan.getEmployeeActivities();

        Map<Employee, List<EmployeeActivity>> groupedMissingEmployeeActivities = employeeActivities.stream()
                .filter(employeeActivity -> !existEmployeesPayslipIds.contains(employeeActivity.getEmployee().getId().toString()))
                .collect(Collectors.groupingBy(EmployeeActivity::getEmployee));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        int totalPayslips = groupedMissingEmployeeActivities.size() + existEmployeesPayslipIds.size();

        storage.put(date, new PayslipsGenerationProgress(new AtomicInteger(existEmployeesPayslipIds.size()), totalPayslips));
        AtomicInteger generatedPayslipsCount = storage.get(date).getGeneratedPayslipsCount();

        for (Map.Entry<Employee, List<EmployeeActivity>> entry : groupedMissingEmployeeActivities.entrySet()) {
            Employee employee = entry.getKey();
            executorService.submit(() -> {
                List<EmployeeActivity> newEmployeeActivities = entry.getValue();
                try {
                    List<Integer> employeeActivityIds = newEmployeeActivities.stream()
                            .map(EmployeeActivity::getId).collect(Collectors.toList());
                    payslipGenerator.generateResult(employeeActivityIds);
                } catch (IOException exception) {
                    throw new PayslipNotCreatedException("Payslip file not created", exception);
                }
                        generatedPayslipsCount.incrementAndGet();
                        log.info(String.format("Payslip for %s %s is created", employee.getFirstName(), employee.getLastName()));
                    }
            );
        }
    }

    @Override
    public String getProgress(LocalDate date) {
        AtomicInteger generatedPayslipsCount = storage.get(date).getGeneratedPayslipsCount();
        Integer totalPayslips = storage.get(date).getTotalPayslips();
        return String.format("%d of %d ----- %d%%",
                generatedPayslipsCount.get(), totalPayslips, (generatedPayslipsCount.get() * 100) / totalPayslips);
    }
}
