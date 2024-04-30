package com.dasa.rms.configurations.logs;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

@Component
public class SL4J { //simple log for java
    private static String filePath;
    private static String fileName;
    private Logger logger;

    public void writeLog(LogLevel4j level, Class<?> className, String methodName, String message) {
        logger = LoggerFactory.getLogger(className);
        switch (level) {
            case INFO:
                logger.info("METHOD NAME: {} \nINFO: {}", methodName, message);
                break;
            case WARN:
                logger.warn("METHOD NAME: {} \nWARN: {}", methodName, message);
                break;
            case ERROR:
                logger.error("METHOD NAME: {} \nERROR: {}", methodName, message);
                break;
        }
    }

    static {
        // Initialize properties
        Properties properties = new Properties();
        String fileName = "log4j.properties";

        // Get the base directory of the software
        String baseDirectory = "";

        // Construct the full path of the properties file
        String logfilePath = Paths.get(baseDirectory, fileName).toString();
        try {
            File file = new File(logfilePath);
            if(!file.exists()){
                initializeLog4jProperties();
            }
        }
        catch(Exception e){
            initializeLog4jProperties();
        }
        // Construct the full path of the properties file

        // Use try-with-resources to automatically close the FileInputStream
        try (FileInputStream fileInputStream = new FileInputStream(logfilePath)) {
            properties.load(fileInputStream);
            //logpath for local machine
            try {
                filePath = properties.getProperty("AgentPortal_logPath");
            }catch (Exception e){
                filePath = logfilePath;
            }

            if(filePath==null)
                filePath = logfilePath;
            // Check if the file exists; if not, create it
            File logFile = new File(filePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            // Create a LoggerContext
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

            // Create a FileAppender
            //FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
            //fileAppender.setContext(loggerContext);
            //fileAppender.setFile(filePath); // Specify the desired log file path


            // Create a RollingFileAppender with SizeAndTimeBasedRollingPolicy
            RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
            fileAppender.setContext(loggerContext);
            fileAppender.setFile(filePath); // Specify the desired log file path

            SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
            rollingPolicy.setContext(loggerContext);
            rollingPolicy.setParent(fileAppender);
            if(filePath!=null)
                fileName = filePath.replaceAll("\\.log$", "");
            rollingPolicy.setFileNamePattern(fileName + ".%d{yyyy-MM-dd}.%i.log");
            rollingPolicy.setMaxFileSize(FileSize.valueOf("15MB")); // Limit the file size to 15MB
            rollingPolicy.start();
            fileAppender.setRollingPolicy(rollingPolicy);

            // Create an Encoder (PatternLayoutEncoder)
            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setPattern("----------------------------%level--------------------------------\n" +
                    "Date\t : %d{yyyy-MM-dd HH:mm:ss}\n" +
                    "CLASS NAME: %logger{0}\n" +
                    "%msg%n\n");
            encoder.setContext(loggerContext);
            encoder.start();
            fileAppender.setEncoder(encoder);
            fileAppender.start();

            // Get the root logger and add the FileAppender
            ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
            rootLogger.addAppender(fileAppender);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 */12 * * *") // Run every 12 hours
    private void deleteOldLogFiles() {
        try {

            File logFile = new File(filePath);
            File logFileDirectory = logFile.getParentFile();
            String logFileName = logFile.getName().replaceAll("\\.log$", "");

            //  File[] logFiles = logFileDirectory.listFiles((dir, name) -> name.matches(fileName + "\\..+\\.log"));
            //Collect the list of files that are available in the direcotry with logfile name.
            File[] logFiles = logFileDirectory.listFiles((dir, name) -> name.matches(logFileName + "\\..+\\.log"));


            //loop over the files only keeping the last 3 and removing all other.
            if (logFiles != null && logFiles.length > 3) {
                Arrays.sort(logFiles, Comparator.comparingLong(File::lastModified));

                for (int i = 0; i < logFiles.length - 3; i++) {
                    if (!logFiles[i].delete()) {
                        // Handle the case where deletion fails (e.g., log file is in use)
                        // You can log an error message or take appropriate action here
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void initializeLog4jProperties() {
        // Specify the file name
        String fileName = "log4j.properties";

        // Get the base directory of the software
        String baseDirectory = "";

        // Construct the full path of the properties file
        String filePath = Paths.get(baseDirectory, fileName).toString();

        // Check if the file already exists
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                // Create a new properties file
                Properties properties = new Properties();

                // Load default properties from resources
                try (InputStream defaultPropertiesStream = SL4J.class.getClassLoader().getResourceAsStream(fileName)) {
                    if (defaultPropertiesStream != null) {
                        properties.load(defaultPropertiesStream);
                    }
                }

                // Save the properties to the file
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    properties.store(outputStream, "Default log4j properties");
                }

                System.out.println("Created log4j.properties file in the base directory.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("log4j.properties file already exists in the base directory.");
        }
    }

}





