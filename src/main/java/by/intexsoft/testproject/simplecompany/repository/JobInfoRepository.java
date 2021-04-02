package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoRepository extends JpaRepository<Position, String> {
}
