package StudentSystem;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String result =  String.format("%s is %d years old.", this.name, this.age);
        String studentType = "";
        if (this.grade >= 5.00) {
            studentType = " Excellent student.";
        } else if (this.grade < 5.00 && this.grade >= 3.50) {
            studentType = " Average student.";
        } else {
            studentType =" Very nice person.";
        }
        return result += studentType;
    }
}
