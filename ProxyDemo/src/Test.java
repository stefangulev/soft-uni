import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {


        StudentService studentService = pretendThatIAmSpringAndInjectStudentService();
        System.out.println(studentService.getAllStudents());
        System.out.println(studentService.getAllStudents());
    }

    private static StudentService pretendThatIAmSpringAndInjectStudentService() {
        StudenServceImpl toProxy = new StudenServceImpl();
        return (StudentService) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                new Class[]{StudentService.class},
                new CacheableInvocationHandler(toProxy)
        );
    }
}
