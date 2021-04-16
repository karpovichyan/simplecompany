package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeActivityRepository extends JpaRepository<EmployeeActivity, Integer> {
    List<EmployeeActivity> findAllByPlanId(Integer id);
}
