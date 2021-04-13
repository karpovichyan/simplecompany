package by.intexsoft.testproject.simplecompany.repository;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByFirstName(String firstName);

    List<Employee> findAllByLastName(String lastName);

    List<Employee> findAllByPositionName(String position);

    List<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByFirstNameAndPositionName(String firstName, String position);

    List<Employee> findAllByLastNameAndPositionName(String lastName, String position);

    List<Employee> findAllByFirstNameAndLastNameAndPositionName(String firstName, String lastName, String position);
}
