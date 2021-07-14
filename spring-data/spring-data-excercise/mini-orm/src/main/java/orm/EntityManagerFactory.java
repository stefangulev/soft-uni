package orm;

import entities.Main;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManagerFactory {


    public static Connection createConnection(String username, String password, String databaseName) throws SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/";
        return DriverManager.getConnection(connectionString + databaseName, username, password);
    }


    public static void createTables(Connection connection, List<Class<?>> classes) throws SQLException {
        String sql = "" ;
        for (Class aClass : classes) {
           Entity entityInfo =  (Entity) aClass.getAnnotation(Entity.class);
           String columns = Arrays.stream(aClass.getDeclaredFields()).map(field -> {
               if(field.isAnnotationPresent(Id.class)) {
                   return String.format("%s INT PRIMARY KEY AUTO_INCREMENT%n", field.getAnnotation(Id.class).name());
               } else {
                   return String.format("%s %s", field.getAnnotation(Column.class).name(), field.getAnnotation(Column.class).declaredType());
               }
           }).collect(Collectors.joining(", "));

            sql += String.format("CREATE TABLE %s (\n %s \n);", entityInfo.name(), columns);

        }
        System.out.println(sql);
        connection.prepareStatement(sql).execute();

    }

    public static List<Class<?>> getClasses(Class<?> mainClass) throws ClassNotFoundException, URISyntaxException {
        String path = mainClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String packageName = mainClass.getPackageName();


        File rootDir = new File(path + packageName.replace(".", "/"));
        List<Class<?>> classes = new ArrayList<>();

        scanEntities(
                rootDir,
                packageName,
                classes
        );
        return classes;

    }

    private static void scanEntities(File dir, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanEntities(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                Class<?> classInfo = Class.forName(packageName + "." + file.getName().replace(".class", ""));
                if (classInfo.isAnnotationPresent(Entity.class)) {
                    classes.add(classInfo);
                }
            }
        }
    }
}
