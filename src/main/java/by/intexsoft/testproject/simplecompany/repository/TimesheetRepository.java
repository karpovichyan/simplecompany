package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Integer>{
}
