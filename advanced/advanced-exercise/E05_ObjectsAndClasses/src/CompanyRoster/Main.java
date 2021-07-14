package CompanyRoster;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        Map<String, Department> departmentMap = new LinkedHashMap<>();

        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");

           String name = tokens[0];
           double salary = Double.parseDouble(tokens[1]);
           String position = tokens[2];
           String department = tokens[3];

           Employee employee;
           if (tokens.length == 4) {
               employee = new Employee(name, salary, position, department);
           } else if (tokens.length == 6) {
               String email = tokens[4];
               int age = Integer.parseInt(tokens[5]);
              employee = new Employee(name, salary, position, department, email, age);
           } else {
               try {
                   int age = Integer.parseInt(tokens[4]);
                  employee = new Employee(name, salary, position, department, age);
               } catch (NumberFormatException ex) {
                   String email = tokens[4];
                 employee =  new Employee(name, salary, position, department, email);
               }



           }
            departmentMap.putIfAbsent(department, new Department());
            departmentMap.get(department).addToDepartment(employee);




        }

        String departmentName = "";
        Department richestDepartment = new Department();
        double currentHighestAverageSalary = Double.MIN_VALUE;
        for (Map.Entry<String, Department> stringDepartmentEntry : departmentMap.entrySet()) {
            double current = stringDepartmentEntry.getValue().getAverageSalary();
            if (current > currentHighestAverageSalary) {
                currentHighestAverageSalary = current;
                richestDepartment = stringDepartmentEntry.getValue();
                departmentName = stringDepartmentEntry.getKey();
        }

        }
        List<Employee> richestDepartmentList = richestDepartment.getEmployeesList();

        System.out.println(String.format("Highest Average Salary: %s", departmentName));
        richestDepartmentList.stream().sorted((l,r) -> Double.compare(r.getSalary(), l.getSalary())).forEach(System.out::println);


    }
}
