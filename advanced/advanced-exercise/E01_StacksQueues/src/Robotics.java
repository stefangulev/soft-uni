import javax.swing.*;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Robotics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        Map<String, Integer> robotNamesAndTimes = new LinkedHashMap<>();
        String inputRobotData = scan.nextLine();
        String[] splittedRobotData = inputRobotData.split(";");

        for (int i = 0; i <splittedRobotData.length; i++) {
            String[] noDashes = splittedRobotData[i].split("-");
            String robotName = noDashes[0];
            int robotWT = Integer.parseInt(noDashes[1]);

            robotNamesAndTimes.put(robotName, robotWT);
        }
        int[] workingTime = new int[robotNamesAndTimes.size()];

        String time = scan.nextLine();
        int hours = Integer.parseInt(time.split(":")[0]);
                int minutes =Integer.parseInt(time.split(":")[1]);
                        int seconds =Integer.parseInt(time.split(":")[2]);
        long totalTimeInSeconds = hours * 3600 + minutes * 60 + seconds;

        ArrayDeque<String> queue = new ArrayDeque<>();

        String input = scan.nextLine();

        while (!"End".equals(input)) {
            queue.offer(input);
            input = scan.nextLine();
        }



       while (!queue.isEmpty()) {
           String currentProduct = queue.poll();
           totalTimeInSeconds++;
           boolean productIsTaken = false;
           for (int i = 0; i <workingTime.length; i++) {

               if (workingTime[i] == 0) {
                   productIsTaken = true;
                   String robotName = "";
                   int count = 0;
                   for (Map.Entry<String, Integer> entry : robotNamesAndTimes.entrySet()) {
                       if (i == count) {
                           robotName = entry.getKey();
                           workingTime[i] = entry.getValue();
                           break;
                       }
                       count++;
                   }
                   long takenHours = totalTimeInSeconds / 3600;
                           long takenMinutes = totalTimeInSeconds % 3600 /60;
                                   long takenSeconds = totalTimeInSeconds % 60;
                   System.out.println(String.format("%s - %s [%02d:%02d:%02d]", robotName, currentProduct, takenHours, takenMinutes, takenSeconds));
                   break;

               }


           }
           if (!productIsTaken) {
               queue.offer(currentProduct);
           }
           for (int i = 0; i < workingTime.length; i++) {
               if (workingTime[i] > 0) {
                   workingTime[i] = workingTime[i] - 1;
               }
           }

       }
    }
}
