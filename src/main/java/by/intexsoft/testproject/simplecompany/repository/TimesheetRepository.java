package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Plan, Integer> {
}
