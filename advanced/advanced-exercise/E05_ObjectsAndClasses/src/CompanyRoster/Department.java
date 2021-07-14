package CompanyRoster;

import java.util.ArrayList;
import java.util.List;

public class Department {
    List<Employee> employeesList;

    public Department () {
        this.employeesList = new ArrayList<>();
    }

    public void addToDepartment (Employee employee) {
        this.employeesList.add(employee);
    }

    public double getAverageSalary () {
        double totalDepartmentSalary = 0;
        for (Employee employee : employeesList) {
            double currentSalary = employee.getSalary();
            totalDepartmentSalary += currentSalary;
        }
        return totalDepartmentSalary / employeesList.size();

    }

    public List<Employee> getEmployeesList() {
        return this.employeesList;
    }

}
