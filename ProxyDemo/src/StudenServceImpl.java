import java.util.List;

public class StudenServceImpl implements StudentService{

    @Cacheable("students")
    @Override
    public List<Student> getAllStudents() {
        System.out.println("Calculating students...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            Thread.interrupted();
        }

        return List.of(new Student("Ivan", 31), new Student("Gosho", 15));
    }
}
