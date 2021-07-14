package orm;

import entities.Main;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager implements DbContext{
    private Connection connection;

    public EntityManager(Connection connection) throws URISyntaxException, ClassNotFoundException, SQLException {

        this.connection = connection;
        EntityManagerFactory.createTables(connection,EntityManagerFactory.getClasses(Main.class));
    }
    @Override
    public boolean persist(Object entity) throws IllegalAccessException, SQLException {
      Field primaryKey = getId(entity.getClass());
      primaryKey.setAccessible(true);
      Object value = primaryKey.get(entity);

      if(value == null || (long) value == 0) {
          return doInsert(entity, primaryKey);
      }
      return doUpdate (entity, primaryKey);
    }

    private boolean doUpdate(Object entity, Field primaryKey) throws IllegalAccessException {
        String sql = "UPDATE ";
        String tableName = entity.getClass().getAnnotation(Entity.class).name();
        String fieldsValues = Arrays.stream(entity.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class)).
                map(field -> {
                    try {
                        field.setAccessible(true);
                        return String.format("%s = %s", field.getAnnotation(Column.class). name(),field.get(entity) );
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.joining(", "));


        sql += tableName + " SET " + fieldsValues + " WHERE " + primaryKey.getAnnotation(Id.class).name() + " = " + primaryKey.get(entity);
        System.out.println(sql);

        return false;

    }

    private boolean doInsert(Object entity, Field primaryKey) throws SQLException {
        String sql = "INSERT INTO ";
        String tableName = entity.getClass().getAnnotation(Entity.class).name();
        String fields = getFields(entity);
        String values = getFieldValues(entity);
        sql += tableName + " (" + fields + ") " + "VALUES " + " (" + values + ")";
        System.out.println(sql);
       return  connection.prepareStatement(sql).execute();
    }

    private String getFieldValues(Object entity) {
        return Arrays.stream(entity.getClass()
                .getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        if(field.getAnnotation(Column.class).declaredType().contains("VARCHAR") || field.getAnnotation(Column.class).declaredType().contains("DATE")) {
                            return String.format("'%s'", String.valueOf(field.get(entity)));
                        } else {
                            return String.format("%s", String.valueOf(field.get(entity)));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.joining(", "));
    }

    private String getFields(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name()).collect(Collectors.joining(", "));
    }

    public Field getId(Class<?> entity) {
       return Arrays.stream(entity.getDeclaredFields()).
               filter(field -> field.isAnnotationPresent(Id.class)).
               findFirst().orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

}
