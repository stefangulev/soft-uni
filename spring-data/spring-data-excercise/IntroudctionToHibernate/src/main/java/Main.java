import entities.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter problem number here:");
        int problemNumber = Integer.parseInt(scan.nextLine());

        switch (problemNumber) {
            case 2: problemTwo(em); break;
            case 3: problemThree(em, scan); break;
            case 4: problemFour(em); break;
            case 5: problemFive(em); break;
            case 6: problemSix(em, scan); break;
            case 7: problemSeven(em); break;
            case 8: problemEight(em, scan); break;
            case 9: problemNine(em); break;
            case 10: problemTen(em); break;
            case 11: problemEleven(em, scan); break;
            case 12: problemTwelve(em); break;
            case 13: problemThirteen(em,scan); break;
            default: break;

        }


    }




    public static void problemTwo(EntityManager em) {
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

    private static void problemThree(EntityManager em, Scanner scan) {
        String result = getListOfEmployeesByCriteria("fullname", em, scan).size() >= 1 ? "Yes" : "No";
        System.out.println(result);

    }

    private static void problemFour(EntityManager em) {
        getEmployeesWithSalaryMoreThan(50000, em).forEach(e -> System.out.println(e.getFirstName()));
    }

    private static void problemFive(EntityManager em) {
        TypedQuery<Department> getDepartId = em.createQuery("SELECT d FROM Department d WHERE d.name = :name", Department.class);
        getDepartId.setParameter("name", "Research and Development");
        int id = getDepartId.getSingleResult().getId();
        List<Employee> employees = getEmployeesByDepartmentId(id, em);
        employees.forEach(e -> System.out.println(String.format("%s %s from %s - ", e.getFirstName(), e.getLastName(), e.getDepartment().getName()) + "$" + e.getSalary()));

    }
    private static void problemSix(EntityManager em, Scanner scan) {
        em.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);
        Employee employee = getSingleEmployeeByCriteria("lastname", em, scan);
        employee.setAddress(address);
        em.persist(employee);
        em.getTransaction().commit();
    }
    private static void problemSeven(EntityManager em) {
        TypedQuery<Address> query = em.createQuery("SELECT a as count FROM Address a", Address.class);
        List<Address> resultList = query.getResultList();
        resultList.stream().sorted((l,r) -> Integer.compare(r.getEmployees().size(), l.getEmployees().size())).limit(10)
                .forEach(e -> System.out.println(String.format("%s, %s - %d employees", e.getText(), e.getTown().getName(), e.getEmployees().size())));


    }
    private static void problemEight(EntityManager em, Scanner scan) {
        int id = Integer.parseInt(scan.nextLine());
        Employee employee = em.find(Employee.class, id);
      String info = String.format("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        String projects = new ArrayList<>(employee.getProjects()).stream().sorted(Comparator.comparing(Project::getName)).map(e -> "  " + e.getName()).collect(Collectors.joining("\n"));
        System.out.println(info + projects);

    }
    private static void problemNine(EntityManager em) {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class);
        System.out.println(query.getResultList().stream().limit(10).sorted(Comparator.comparing(Project::getName)).map(e -> String.
                format("Project name: %s%n   Project Description: %s%n   Project Start Date: %s%n   Project End Date: %s%n", e.getName(), e.getDescription(), e.getStartDate().toString(), e.getEndDate() == null ? null : e.getEndDate().toString())).collect(Collectors.joining("")));
    }
    private static void problemTen(EntityManager em) {
        em.getTransaction().begin();
        List<String> deptNames = Arrays.asList("Engineering", "Tool Design", "Marketing","Information Services");
      List<Employee> employees =  em.createQuery("SELECT e FROM Employee e ORDER BY e.department.id", Employee.class)
                .getResultList().stream().filter(e -> deptNames.contains(e.getDepartment().getName())).collect(Collectors.toList());

               employees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));
               employees.forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), Double.parseDouble(e.getSalary().toString())));
               em.getTransaction().commit();

    }
    private static void problemEleven(EntityManager em, Scanner scan) {
        String pattern = scan.nextLine();
        pattern += "%";
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :pattern", Employee.class);
        query.setParameter("pattern", pattern);
        query.getResultList().forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), Double.parseDouble(e.getSalary().toString())));

    }
    private static void problemTwelve(EntityManager em) {
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
        List<String> collect = query.getResultList().stream().filter(e -> getMaxSalaryFromDepartment(e).get() < 30000 || getMaxSalaryFromDepartment(e).get() > 70000)
                .map(e -> String.format("%s %.2f", e.getName(), getMaxSalaryFromDepartment(e).get())).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
    private static void problemThirteen(EntityManager em, Scanner scan) {
        em.getTransaction().begin();
        String townName = scan.nextLine();
       Town town = em.createQuery("SELECT t FROM Town t WHERE t.name = :name", Town.class).setParameter("name", townName).getSingleResult();
        List<Address> addresses= em.createQuery("SELECT a FROM Address a WHERE a.town.id = :id", Address.class).setParameter("id", town.getId()).getResultList();
        addresses.forEach(em::remove);
       em.remove(town);
        System.out.printf("%d addresses in %s deleted", addresses.size(), townName);
        em.getTransaction().commit();
    }

    private static List<Employee> getListOfEmployeesByCriteria(String identifier , EntityManager em, Scanner scan) {
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
    private static Employee getSingleEmployeeByCriteria(String identifier ,EntityManager em, Scanner scan) {
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
    private static List<Employee> getEmployeesWithSalaryMoreThan(double salary, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Employee> r = criteria.from(Employee.class);
        criteria.select(r).where(builder.gt(r.get("salary"), salary));
        return em.createQuery(criteria).getResultList();
    }
    private static List<Employee> getEmployeesByDepartmentId(int id, EntityManager em) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.department.id = :id ORDER BY e.salary, e.id", Employee.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    private static Optional<Double> getMaxSalaryFromDepartment(Department department) {
        Set<Employee> employees = department.getEmployees();
        return employees.stream().map(e -> e.getSalary().doubleValue()).max(Double::compare);
    }
}
