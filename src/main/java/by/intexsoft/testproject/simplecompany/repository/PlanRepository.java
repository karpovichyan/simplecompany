package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    Plan findByTotalHoursAndDate(int totalHours, LocalDate date);

    Optional<Plan> findByDate(LocalDate date);
}
