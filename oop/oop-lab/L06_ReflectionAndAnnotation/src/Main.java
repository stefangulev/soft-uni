import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    @Author(name = "George")
    public static void main(String[] args) {

        Tracker.printMethodsByAuthor(Main.class);

    }
}
