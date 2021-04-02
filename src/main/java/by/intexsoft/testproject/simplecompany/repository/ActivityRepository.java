package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<EmployeeActivity, Integer> {
}
