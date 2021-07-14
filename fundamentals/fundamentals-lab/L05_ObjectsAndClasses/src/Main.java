import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        List<Students> list = new ArrayList<>();

        while (!line.equals("end")) {
            String[] input = line.split(" ");

            String firstName = input[0];
            String lastName = input[1];
            int age = Integer.parseInt(input[2]);
            String hometown = input[3];


           if (isStudentExisting(list, firstName, lastName)) {
               Students student = getStudent (list, firstName, lastName);

               student.setFirstName(firstName);
               student.setLastName(lastName);
               student.setAge(age);
               student.setHometown(hometown);


           } else {

               Students student = new Students(firstName, lastName, age, hometown);

               list.add(student);
           }

            line = scan.nextLine();

        }

        String requiredTown = scan.nextLine();

        for (Students students : list) {
            if (students.getHometown().equals(requiredTown)) {
                System.out.printf("%s %s is %d years old%n", students.getFirstName(), students.getLastName(), students.getAge());
            }
        }


    }
    private static boolean isStudentExisting(List<Students>list, String firstName, String lastName) {
        boolean doesExist = false;
        for (Students students : list) {
            doesExist = students.getFirstName().equals(firstName) && students.getLastName().equals(lastName);
            if (doesExist) {
                break;
            }
        }

        return doesExist;
    }
    private static Students getStudent (List<Students> list, String firstName, String lastName) {
        Students existingStudent = null;

        for (Students students : list) {
            if (students.getFirstName().equals(firstName) && students.getLastName().equals(lastName)) {
                existingStudent = students;
            }
        }
        return existingStudent;
    }

}
