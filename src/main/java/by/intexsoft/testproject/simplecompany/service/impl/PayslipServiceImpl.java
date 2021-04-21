package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.PayslipService;
import by.intexsoft.testproject.simplecompany.service.impl.details.PayslipsCount;
import by.intexsoft.testproject.simplecompany.service.payslip.execute.PayslipGenerator;
import org.springframework.stereotype.Service;

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
    private final PayslipGenerator payslipGenerator;
    private final PlanRepository planRepository;
    private final Map<LocalDate, PayslipsCount> storage = new HashMap<>();

    public PayslipServiceImpl(PlanRepository planRepository, PayslipGenerator payslipGenerator) {
        this.planRepository = planRepository;
        this.payslipGenerator = payslipGenerator;
    }

    @Override
    public void create(LocalDate date) {
        Plan plan = planRepository.findByDate(date)
                .orElseThrow(() -> new PlanNotFoundException("Plan with date " + date + " not found!"));

        Set<EmployeeActivity> employeeActivities = plan.getEmployeeActivities();

        Map<Employee, List<EmployeeActivity>> groupedEmployeeActivities = employeeActivities.stream()
                .collect(Collectors.groupingBy(EmployeeActivity::getEmployee));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int totalEmployees = groupedEmployeeActivities.size();

        storage.put(date, new PayslipsCount(new AtomicInteger(), totalEmployees));
        AtomicInteger count = storage.get(date).getCount();

        for (Map.Entry<Employee, List<EmployeeActivity>> entry : groupedEmployeeActivities.entrySet()) {
            executorService.submit(() -> {
                        List<EmployeeActivity> newEmployeeActivities = entry.getValue();
                        payslipGenerator.generateResult(newEmployeeActivities.stream()
                                .map(EmployeeActivity::getId).collect(Collectors.toList()));

                        count.incrementAndGet();
                    }
            );
        }
    }

    @Override
    public String getProgress(LocalDate date) {
        AtomicInteger count = storage.get(date).getCount();
        Integer totalEmployees = storage.get(date).getTotalEmployees();
        return count.get() + " of " + totalEmployees + " ------ "
                + (count.get() * 100) / totalEmployees + "%" + " of 100%";
    }
}

