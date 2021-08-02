package hiberspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard extends BaseEntity {
    private String number;
    private Employee employee;

    @Column(name = "number", nullable = false, unique = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToOne(mappedBy = "employeeCard")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
