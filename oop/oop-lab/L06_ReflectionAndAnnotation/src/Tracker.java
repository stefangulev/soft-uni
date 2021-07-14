import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tracker {
    public static void printMethodsByAuthor(Class<?> clazz) {

        Map<Annotation, List<Method>> methodsPerAnnotation = new LinkedHashMap<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            methodsPerAnnotation.putIfAbsent(declaredAnnotations[0], new ArrayList<>());
            methodsPerAnnotation.get(declaredAnnotations[0]).add(declaredMethod);
        }

       methodsPerAnnotation.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()
               .toString().replaceAll("[\\[\\],]", "")));


    }
}
