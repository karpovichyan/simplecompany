package by.intexsoft.testproject.simplecompany.service.specification;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Employee_;
import by.intexsoft.testproject.simplecompany.entity.Position_;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> whereFirstNameEqualTo(String firstName) {
        return (root, query, builder) -> {
            if (firstName != null) {
                return builder.equal(root.get(Employee_.firstName), firstName);
            }
            return null;
        };
    }

    public static Specification<Employee> whereLastNameEqualTo(String lastName) {
        return (root, query, builder) -> {
            if (lastName != null) {
                return builder.equal(root.get(Employee_.lastName), lastName);
            }
            return null;
        };
    }

    public static Specification<Employee> whereSalaryEqualsTo(Integer salary) {
        return (root, query, builder) -> {
            if (salary != null) {
                return builder.equal(root.get(Employee_.position).get(Position_.salary), salary);
            }
            return null;
        };
    }
}
