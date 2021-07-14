package Google;

import java.util.Collections;

public class Company {
    private String name;
    private String department;
    private double salary;

    public Company() {

    }


    public Company(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getCompany() {
        if (name != null) {
            return String.format("Company:%n%s %s %.2f", this.name, this.department, this.salary);
        } else {
            return "Company:";
        }
    }

}
