package interfacePerson;

import java.lang.reflect.Method;
import java.util.*;

public class Main {

        public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);

                Map<String, Buyer> buyers = new LinkedHashMap<>();

                int n = Integer.parseInt(scan.nextLine());

                while (n-- > 0) {
                        String[] tokens = scan.nextLine().split("\\s+");
                        if (tokens.length == 3) {
                                buyers.put(tokens[0], new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
                        } else {
                                buyers.put(tokens[0], new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
                        }
                }

                String input = scan.nextLine();
                while (!input.equals("End")) {
                        if (buyers.containsKey(input)) {
                                buyers.get(input).buyFood();
                        }

                        input = scan.nextLine();
                }

                System.out.println(buyers.values().stream().mapToInt(Buyer::getFood).sum());


        }


}
