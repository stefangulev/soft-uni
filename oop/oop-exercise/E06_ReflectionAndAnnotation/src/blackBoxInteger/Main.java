package blackBoxInteger;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Scanner scan = new Scanner(System.in);
        Class<BlackBoxInt> clazz = BlackBoxInt.class;


        Constructor<BlackBoxInt> ctor = clazz.getDeclaredConstructor();
        ctor.setAccessible(true);
        BlackBoxInt blackBoxInt = ctor.newInstance();


        String input = scan.nextLine();
      while (!input.equals("END")) {
          String[] tokens = input.split("_");
         Method method = clazz.getDeclaredMethod(tokens[0], int.class);
         method.setAccessible(true);
         method.invoke(blackBoxInt, Integer.parseInt(tokens[1]));
          Field field = clazz.getDeclaredField("innerValue");
          field.setAccessible(true);
          System.out.println(field.get(blackBoxInt));

          input = scan.nextLine();
      }



    }

}
