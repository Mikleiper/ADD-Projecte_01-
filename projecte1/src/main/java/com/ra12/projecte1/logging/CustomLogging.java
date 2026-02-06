package com.ra12.projecte1.logging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CustomLogging {

    public CustomLogging(){
    }

    private static final String LOG_DIRECTORY = "logs/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    //mètode intern per gestionar la creació del directori, nom del fitxer i contingut del log
    private void writeToFile(String msg){
        LocalDateTime now = LocalDateTime.now();
        String fileName = String.format("aplicacio-%s-%s-%s.log", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Path logPath = Paths.get(LOG_DIRECTORY, fileName);
        try{ 
            if (logPath.getParent() != null && !Files.exists(logPath.getParent())) {
                Files.createDirectories(logPath.getParent());            }          

            try(BufferedWriter bw = Files.newBufferedWriter(logPath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    bw.write(msg);
                    bw.newLine(); 
                }
        }catch (IOException e) {
            System.err.println("ERROR escrivint al fitxer de log; "+ e.getMessage());
        }
    }

    // mètode per gestionars missatges d'error al log
    public void error(String className, String method, String errorMsg, Exception e){
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] ERROR - %s - %s - %s", timestamp, className, method, errorMsg);        
        if(e != null){
            logEntry += " - Exception: " + e.getMessage();
        }
        writeToFile(logEntry);
        System.out.println(logEntry);
    }  

    // mètode per gestionars missatges d'informació al log
    public void info(String className, String method, String infoMsg){
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] INFO - %s - %s - %s", timestamp, className, method, infoMsg);
        writeToFile(logEntry);
        System.out.println(logEntry);
    }
}
