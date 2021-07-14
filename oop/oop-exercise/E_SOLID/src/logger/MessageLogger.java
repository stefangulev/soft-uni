package logger;

import enums.ReportLevel;
import interfaces.Appender;

import java.util.Arrays;
import java.util.List;

public class MessageLogger implements Logger {
   private List<Appender> appenders;

   public MessageLogger(Appender... appenders) {
       this.appenders = Arrays.asList(appenders);
   }

    @Override
    public void logInfo(String date, String message) {
       this.callAllAppenders(date, ReportLevel.INFO, message);
    }

    @Override
    public void logWarning(String date, String message) {
        this.callAllAppenders(date, ReportLevel.WARNING, message);
    }

    @Override
    public void logError(String date, String message) {
        this.callAllAppenders(date, ReportLevel.ERROR, message);
    }

    @Override
    public void logCritical(String date, String message) {
        this.callAllAppenders(date, ReportLevel.CRITICAL, message);
    }

    @Override
    public void logFatal(String date, String message) {
        this.callAllAppenders(date, ReportLevel.FATAL, message);
    }

    public void callAllAppenders(String date, ReportLevel reportLevel, String message) {
        for (Appender appender : appenders) {
            appender.append(date, reportLevel, message);
        }
    }
}
