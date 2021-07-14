package hierarchy;

public class Tomcat extends Cat {

    private static final String TOMCAT_GENDER = "Male";


    public Tomcat(String name, int age, String gender) {
        super(name, age, TOMCAT_GENDER);
    }

    @Override

    public String produceSound() {
        return "MEOW";
    }
}
