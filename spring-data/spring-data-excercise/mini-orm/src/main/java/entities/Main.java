package entities;

import entities.classes.User;
import orm.EntityManager;
import orm.EntityManagerFactory;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, URISyntaxException, ClassNotFoundException {

        Connection cn = EntityManagerFactory.createConnection("root", "root", "mini_orm_database");
        EntityManager manager = new EntityManager(cn);
        User user = new User("Stefan", 24, LocalDate.now());
        manager.persist(user);
    }
}
