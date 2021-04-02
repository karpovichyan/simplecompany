package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "activity_type_info")
public class ActivityTypeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String type;

    @Column
    private BigDecimal ratio;

    @OneToMany(mappedBy = "activityTypeInfo")
    private Set<EmployeeActivity> employeeActivities;

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTypeInfo that = (ActivityTypeInfo) o;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
