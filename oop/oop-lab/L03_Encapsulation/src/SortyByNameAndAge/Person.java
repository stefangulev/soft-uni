package SortyByNameAndAge;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person (String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);

    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setFirstName(String firstName) {
        nameValidator(firstName, "First");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        nameValidator(lastName, "Last");
        this.lastName = lastName;
    }

    public void nameValidator(String name, String prefix) {
        if (name.length() < 3) {
            throw new IllegalArgumentException(String.format("%s name cannot be less than 3 symbols", prefix));
        }
    }

    public void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public void increaseSalary(double percentage) {
        if (this.age < 30) {
            percentage /=2;
        }
        this.salary *= 1 + percentage / 100;
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %s leva.", this.firstName, this.lastName, this.salary);
    }
}
