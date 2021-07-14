import entities.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
    private static final EntityManager em = factory.createEntityManager();
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter problem number here:");
        int problemNumber = Integer.parseInt(scan.nextLine());

        switch (problemNumber) {
            case 2: problemTwo(); break;
            case 3: problemThree(); break;
            case 4: problemFour(); break;
            case 5: problemFive(); break;
            case 6: problemSix(); break;
            case 7: problemSeven(); break;
            case 8: problemEight(); break;
            case 9: problemNine(); break;
            case 10: problemTen(); break;
            case 11: problemEleven(); break;
            case 12: problemTwelve(); break;
            case 13: problemThirteen(); break;
            default: break;

        }


    }




    public static void problemTwo() {
       em.getTransaction().begin();
        TypedQuery<Town> getAllTowns = em.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> collection = getAllTowns.getResultList();

        for (Town town : collection) {
            if (town.getName().length() > 5) {
                em.detach(town);
            } else {
                town.setName(town.getName().toUpperCase(Locale.ROOT));
                em.persist(town);
            }
        }
        em.flush();
        em.getTransaction().commit();

    }

    private static void problemThree() {
        String result = getListOfEmployeesByCriteria("fullname").size() >= 1 ? "Yes" : "No";
        System.out.println(result);

    }

    private static void problemFour() {
        getEmployeesWithSalaryMoreThan(50000).forEach(e -> System.out.println(e.getFirstName()));
    }

    private static void problemFive() {
        TypedQuery<Department> getDepartId = em.createQuery("SELECT d FROM Department d WHERE d.name = :name", Department.class);
        getDepartId.setParameter("name", "Research and Development");
        int id = getDepartId.getSingleResult().getId();
        List<Employee> employees = getEmployeesByDepartmentId(id);
        employees.forEach(e -> System.out.println(String.format("%s %s from %s - ", e.getFirstName(), e.getLastName(), e.getDepartment().getName()) + "$" + e.getSalary()));

    }
    private static void problemSix() {
        em.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);
        Employee employee = getSingleEmployeeByCriteria("lastname");
        employee.setAddress(address);
        em.persist(employee);
        em.getTransaction().commit();
    }
    private static void problemSeven() {
        TypedQuery<Address> query = em.createQuery("SELECT a as count FROM Address a", Address.class);
        List<Address> resultList = query.getResultList();
        resultList.stream().sorted((l,r) -> Integer.compare(r.getEmployees().size(), l.getEmployees().size())).limit(10)
                .forEach(e -> System.out.println(String.format("%s, %s - %d employees", e.getText(), e.getTown().getName(), e.getEmployees().size())));


    }
    private static void problemEight() {
        int id = Integer.parseInt(scan.nextLine());
        Employee employee = em.find(Employee.class, id);
      String info = String.format("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        String projects = new ArrayList<>(employee.getProjects()).stream().sorted(Comparator.comparing(Project::getName)).map(e -> "  " + e.getName()).collect(Collectors.joining("\n"));
        System.out.println(info + projects);

    }
    private static void problemNine() {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class);
        System.out.println(query.getResultList().stream().limit(10).sorted(Comparator.comparing(Project::getName)).map(e -> String.
                format("Project name: %s%n   Project Description: %s%n   Project Start Date: %s%n   Project End Date: %s%n", e.getName(), e.getDescription(), e.getStartDate().toString(), e.getEndDate() == null ? null : e.getEndDate().toString())).collect(Collectors.joining("")));
    }
    private static void problemTen() {
        em.getTransaction().begin();
        List<String> deptNames = Arrays.asList("Engineering", "Tool Design", "Marketing","Information Services");
      List<Employee> employees =  em.createQuery("SELECT e FROM Employee e ORDER BY e.department.id", Employee.class)
                .getResultList().stream().filter(e -> deptNames.contains(e.getDepartment().getName())).collect(Collectors.toList());

               employees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));
               employees.forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), Double.parseDouble(e.getSalary().toString())));
               em.getTransaction().commit();

    }
    private static void problemEleven() {
        String pattern = scan.nextLine();
        pattern += "%";
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :pattern", Employee.class);
        query.setParameter("pattern", pattern);
        query.getResultList().forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), Double.parseDouble(e.getSalary().toString())));

    }
    private static void problemTwelve() {
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
        List<String> collect = query.getResultList().stream().filter(e -> getMaxSalaryFromDepartment(e).get() < 30000 || getMaxSalaryFromDepartment(e).get() > 70000)
                .map(e -> String.format("%s %.2f", e.getName(), getMaxSalaryFromDepartment(e).get())).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
    private static void problemThirteen() {
        em.getTransaction().begin();
        String townName = scan.nextLine();
       Town town = em.createQuery("SELECT t FROM Town t WHERE t.name = :name", Town.class).setParameter("name", townName).getSingleResult();
        List<Address> addresses= em.createQuery("SELECT a FROM Address a WHERE a.town.id = :id", Address.class).setParameter("id", town.getId()).getResultList();
        addresses.forEach(em::remove);
       em.remove(town);
        System.out.printf("%d addresses in %s deleted", addresses.size(), townName);
        em.getTransaction().commit();
    }

    private static List<Employee> getListOfEmployeesByCriteria(String identifier) {
        String[] input = scan.nextLine().split(" ");
        TypedQuery<Employee> query;
        if(identifier.equals("fullname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstname AND e.lastName = :lastname", Employee.class);
            query.setParameter("firstname", input[0]);
            query.setParameter("lastname", input[1]);
            return query.getResultList();
        } else if (identifier.equals("lastname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.lastName = :lastname", Employee.class);
            query.setParameter("lastname", input[0]);
            return query.getResultList();
        } else if (identifier.equals("firstname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstname", Employee.class);
            query.setParameter("firstname", input[0]);
            return query.getResultList();
        }
        return null;

    }
    private static Employee getSingleEmployeeByCriteria(String identifier) {
        String[] input = scan.nextLine().split(" ");
        TypedQuery<Employee> query;
        if(identifier.equals("fullname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstname AND e.lastName = :lastname", Employee.class);
            query.setParameter("firstname", input[0]);
            query.setParameter("lastname", input[1]);
            return query.getSingleResult();
        } else if (identifier.equals("lastname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.lastName = :lastname", Employee.class);
            query.setParameter("lastname", input[0]);
            return query.getSingleResult();
        } else if (identifier.equals("firstname")) {
            query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstname", Employee.class);
            query.setParameter("firstname", input[0]);
            return query.getSingleResult();
        }
        return null;

    }
    private static List<Employee> getEmployeesWithSalaryMoreThan(double salary) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Employee> r = criteria.from(Employee.class);
        criteria.select(r).where(builder.gt(r.get("salary"), salary));
        return em.createQuery(criteria).getResultList();
    }
    private static List<Employee> getEmployeesByDepartmentId(int id) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.department.id = :id ORDER BY e.salary, e.id", Employee.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    private static Optional<Double> getMaxSalaryFromDepartment(Department department) {
        Set<Employee> employees = department.getEmployees();
        return employees.stream().map(e -> e.getSalary().doubleValue()).max(Double::compare);
    }
}
