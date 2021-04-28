package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.DirectoryNotFoundException;
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
    private final HashMap<LocalDate, PayslipsGenerationProgress> storage = new HashMap<>();
    private final PayslipGenerator payslipGenerator;
    private final PlanRepository planRepository;
    private final PayslipWriter payslipWriter;
    Logger log = LoggerFactory.getLogger(PayslipServiceImpl.class);

    public PayslipServiceImpl(PlanRepository planRepository, PayslipGenerator payslipGenerator, PayslipWriter payslipWriter) {
        this.planRepository = planRepository;
        this.payslipGenerator = payslipGenerator;
        this.payslipWriter = payslipWriter;
    }

    @Override
    public void create(LocalDate date) {
        Plan plan = planRepository.findByDate(date)
                .orElseThrow(() -> new PlanNotFoundException("Plan with date " + date + " not found!"));

        Set<EmployeeActivity> employeeActivities = plan.getEmployeeActivities();

        Map<Employee, List<EmployeeActivity>> groupedEmployeeActivities = employeeActivities.stream()
                .collect(Collectors.groupingBy(EmployeeActivity::getEmployee));
        int totalPayslips = groupedEmployeeActivities.size();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Set<Employee> allEmployees = groupedEmployeeActivities.keySet();

        List<Employee> existEmployeesPayslips;
        try {
            existEmployeesPayslips = payslipWriter.getExistPayslips(date, allEmployees);
        } catch (IOException exception) {
            throw new DirectoryNotFoundException("Directory doesn't exist", exception);
        }

        storage.put(date, new PayslipsGenerationProgress(new AtomicInteger(existEmployeesPayslips.size()), totalPayslips));
        AtomicInteger generatedPayslipsCount = storage.get(date).getGeneratedPayslipsCount();

        if (existEmployeesPayslips.size() != totalPayslips) {
            for (Map.Entry<Employee, List<EmployeeActivity>> entry : groupedEmployeeActivities.entrySet()) {
                Employee employee = entry.getKey();
                if (!existEmployeesPayslips.contains(employee)) {
                    executorService.submit(() -> {
                                List<EmployeeActivity> newEmployeeActivities = entry.getValue();
                                try {
                                    payslipGenerator.generateResult(newEmployeeActivities.stream()
                                            .map(EmployeeActivity::getId).collect(Collectors.toList()));
                                } catch (IOException exception) {
                                    throw new PayslipNotCreatedException("Payslip file not created", exception);
                                }
                                generatedPayslipsCount.incrementAndGet();
                                log.info(String.format("Payslip for %s %s is created", employee.getFirstName(), employee.getLastName()));
                            }
                    );
                } else {
                    log.info(String.format("Payslip for %s %s is already exist", employee.getFirstName(), employee.getLastName()));
                }
            }
        } else {
            log.info("Payslips already exists");
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
