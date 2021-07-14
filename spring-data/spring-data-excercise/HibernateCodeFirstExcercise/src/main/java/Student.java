import entity.BasePerson;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BasePerson {
    private double averageGrade;
    private int attendance;
    private Set<Course> courses;

    public Student() {
        this.courses = new LinkedHashSet<>();
    }
    @Column(name = "average_grade")
    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @ManyToMany
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
