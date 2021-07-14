import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem5 {
    static class Students {
        String firstName;
        String lastName;
        String age;
        String hometown;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getAge() {
            return age;
        }
        public void setAge(String age) {
            this.age = age;
        }
        public String getHometown() {
            return hometown;
        }
        public void setHometown(String hometown) {
            this.hometown = hometown;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Students> list = new ArrayList<>();

            String s = "";

            while (!s.equals("end")) {
                String[] input = scan.nextLine().split(" ");

                if (input[0].equals("end")) {
                    break;
                }
                Students students = new Students();
                students.setFirstName(input[0]);
                students.setLastName(input[1]);
                students.setAge(input[2]);
                students.setHometown(input[3]);

                list.add(students);

            }

            String city = scan.nextLine();

            for (Students n : list) {
                if (city.equals(n.getHometown())) {
                    System.out.printf("%s %s is %s years old%n", n.getFirstName(),n.getLastName(), n.getAge());
                }
            }


    }

}
