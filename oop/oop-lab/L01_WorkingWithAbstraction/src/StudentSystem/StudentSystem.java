package StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem()
    {
        this.repo = new HashMap<>();
    }

    public Map<String, Student> getRepo() {
        return this.repo;
    }

    public void ParseCommand(String[]args)
    {
//        Scanner scanner = new Scanner(System.in);
//        String[] args = scanner.nextLine().split(" ");

        if (args[0].equals("Create"))
        {
            create(args[1], Integer.parseInt(args[2]), Double.parseDouble(args[3]));
        }
        else if (args[0].equals("Show"))
        {
            show(args[1]);
        }
    }

    private void show(String name) {
        if (repoContains(name)) {
            System.out.println(this.repo.get(name));
        }
    }

    private void create(String name, int age, double grade) {
        Student student = new Student(name, age, grade);
        if (!repoContains(student.getName())) {
            repo.put(student.getName(), student);
        }
    }

    public boolean repoContains(String name) {
       return this.repo.containsKey(name);
    }

}
