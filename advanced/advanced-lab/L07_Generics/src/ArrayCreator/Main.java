package ArrayCreator;

public class Main {
    public static void main(String[] args) {


        String[] levskis = ArrayCreator.create(5, "levski");

        for (String levski : levskis) {
            System.out.println(levski);
        }
    }

}
