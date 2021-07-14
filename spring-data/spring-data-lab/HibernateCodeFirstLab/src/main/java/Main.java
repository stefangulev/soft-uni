import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_lab");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        PlateNumber number = new PlateNumber("tret");
        em.persist(number);
        Company company = new Company("plane company");
        em.persist(company);
        Car car = new Car("benz", BigDecimal.valueOf(4234234) , "gas", 2, number);
        Plane plane = new Plane("benz", BigDecimal.valueOf(4234234) , "gas", 2);
        Plane plane2 = new Plane("benz2", BigDecimal.valueOf(4234234) , "gas", 2);
        plane.setCompany(company);
        plane2.setCompany(company);
        Driver driver = new Driver("Ivan Petrov");
        Driver driver2 = new Driver("Georgi Petrov");
        Truck truck = new Truck("benz", BigDecimal.valueOf(4234234) , "gas",55);
        Truck truck2 = new Truck("benz", BigDecimal.valueOf(4234234) , "gas",55);
        truck.addDriver(driver2);
        truck2.addDriver(driver);

        em.persist(driver);
        em.persist(driver2);
        em.persist(truck);
        em.persist(truck2);



        em.persist(car);
        em.persist(plane);
        em.persist(plane2);

        em.getTransaction().commit();
    }
}
