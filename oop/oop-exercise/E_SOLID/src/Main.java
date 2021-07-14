import controllers.ConsoleAppender;
import controllers.SimpleLayout;
import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;
import logger.Logger;
import logger.MessageLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Layout simpleLayout = new SimpleLayout();
//        Appender consoleAppender = new ConsoleAppender(simpleLayout);
//        Logger logger = new MessageLogger(consoleAppender);
//        logger.logError("3/26/2015 2:08:11 PM","Error parsing JSON.");
//        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");

        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");

            ReportLevel reportLevel = tokens.length == 3 ? ReportLevel.valueOf(tokens[2].toUpperCase()) : ReportLevel.INFO;
            
        }
    }
}
