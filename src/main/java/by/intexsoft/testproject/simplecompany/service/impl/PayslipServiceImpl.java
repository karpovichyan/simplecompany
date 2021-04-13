package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.PayslipDto;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.EmployeeActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.PayslipService;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipContentGenerator;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayslipServiceImpl implements PayslipService {
    private final PlanRepository planRepository;
    private final EmployeeActivityRepository employeeActivityRepository;
    private final PayslipWriter payslipWriter;
    private final PayslipContentGenerator payslipContentGenerator;

    public PayslipServiceImpl(PlanRepository planRepository, EmployeeActivityRepository employeeActivityRepository, PayslipWriter payslipWriter, PayslipContentGenerator payslipContentGenerator) {
        this.planRepository = planRepository;
        this.employeeActivityRepository = employeeActivityRepository;
        this.payslipWriter = payslipWriter;
        this.payslipContentGenerator = payslipContentGenerator;
    }

    @Override
    public void createPayslips(PayslipDto payslipDto) throws IOException {
        Plan plan = planRepository.findByDate(payslipDto.getDate()).
                orElseThrow(() -> new PlanNotFoundException(
                        "Plan date " + payslipDto.getDate() + " not found!"
                ));
        List<EmployeeActivity> employeeActivities = employeeActivityRepository.findAllByPlanId(plan.getId());

        Map<Employee, List<EmployeeActivity>> employeeActivityMap = employeeActivities.stream()
                .collect(Collectors.groupingBy(EmployeeActivity::getEmployee));

        for (Map.Entry<Employee, List<EmployeeActivity>> entry : employeeActivityMap.entrySet()) {
            List<EmployeeActivity> employeeActivityList = entry.getValue();

            ArrayList<Integer> leaveHours = new ArrayList<>();
            ArrayList<BigDecimal> leaveHoursRatio = new ArrayList<>();
            for (EmployeeActivity employeeActivity : employeeActivityList) {
                leaveHours.add(employeeActivity.getHours());
                if (employeeActivity.getActivity() != null) {
                    leaveHoursRatio.add(BigDecimal.valueOf(employeeActivity.getHours()).multiply(employeeActivity.getActivity().getRatio()));
                }
            }

            EmployeeActivity employeeActivity = employeeActivityList.get(0);
            String content = payslipContentGenerator.generate(leaveHours, leaveHoursRatio, employeeActivity);
            payslipWriter.makePayslip(employeeActivity, content);
        }
    }
}
