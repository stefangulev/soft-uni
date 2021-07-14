package Google;

import java.util.regex.Pattern;

public class Parent {
    private String parentName;
    private String birthday;


    public Parent(String parentName, String birthday) {
        this.parentName = parentName;
        this.birthday = birthday;
    }

    public String getParentName() {
        return parentName;
    }

    public String getBirthday() {
        return birthday;
    }
}
