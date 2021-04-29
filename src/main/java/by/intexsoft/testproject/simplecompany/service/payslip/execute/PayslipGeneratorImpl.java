package by.intexsoft.testproject.simplecompany.service.payslip.execute;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;
import by.intexsoft.testproject.simplecompany.repository.EmployeeActivityRepository;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipContentGenerator;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayslipGeneratorImpl implements PayslipGenerator {
    private final Logger log = LoggerFactory.getLogger(PayslipGeneratorImpl.class);
    private final PayslipWriter payslipWriter;
    private final PayslipContentGenerator payslipContentGenerator;
    private final EmployeeActivityRepository employeeActivityRepository;

    public PayslipGeneratorImpl(PayslipWriter payslipWriter,
                                PayslipContentGenerator payslipContentGenerator,
                                EmployeeActivityRepository employeeActivityRepository) {
        this.payslipWriter = payslipWriter;
        this.payslipContentGenerator = payslipContentGenerator;
        this.employeeActivityRepository = employeeActivityRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateResult(List<Integer> employeeActivityIds) throws IOException {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            log.warn("Thread has interrupted ", exception);
        }

        List<EmployeeActivity> employeeActivities = employeeActivityRepository.findAllById(employeeActivityIds);

        ArrayList<Integer> absenceHours = new ArrayList<>();
        ArrayList<Integer> absenceReasonHours = new ArrayList<>();
        ArrayList<BigDecimal> earnedResult = new ArrayList<>();

        dataCalculate(employeeActivities, absenceHours, absenceReasonHours, earnedResult);
        getPayslip(employeeActivities, absenceHours, absenceReasonHours, earnedResult);
    }

    private void getPayslip(List<EmployeeActivity> employeeActivities,
                            ArrayList<Integer> absenceHours,
                            ArrayList<Integer> absenceReasonHours,
                            ArrayList<BigDecimal> earnedResults) throws IOException {

        EmployeeActivity employeeActivity = employeeActivities.get(0);
        String content = payslipContentGenerator.generate(absenceHours, earnedResults, absenceReasonHours, employeeActivity);
        payslipWriter.create(employeeActivity, content);
    }

    private void dataCalculate(List<EmployeeActivity> employeeActivities,
                               ArrayList<Integer> absenceHours,
                               ArrayList<Integer> absenceReasonHours,
                               ArrayList<BigDecimal> earnedResults) {

        for (EmployeeActivity employeeActivity : employeeActivities) {
            double earnedAnOneHour = (double) employeeActivity.getEmployee().getPosition().getSalary()
                    / employeeActivity.getPlan().getTotalHours();
            BigDecimal earnedAnHours = BigDecimal.valueOf((long) employeeActivity.getHours() * earnedAnOneHour)
                    .multiply(employeeActivity.getActivity().getRatio());
            earnedResults.add(earnedAnHours);
            if (employeeActivity.getActivity().getActivityType() == ActivityType.PRESENT) {
                absenceHours.add(employeeActivity.getPlan().getTotalHours() - employeeActivity.getHours());
            } else {
                absenceReasonHours.add(employeeActivity.getHours());
            }
        }
    }
}
