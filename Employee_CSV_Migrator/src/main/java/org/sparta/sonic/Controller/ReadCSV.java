package org.sparta.sonic.Controller;

import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadCSV {
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();

    public static EmployeeArrayParser connectToFile(String filename){
        int lineCount = 0;
        logger.log(Level.INFO, "Parsing file has begun");

        EmployeeArrayParser employeeArrayParser = new EmployeeArrayParser();

        String line;
        try (FileReader fileReader = new FileReader(filename)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //int i=0;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                employeeArrayParser.addEmployeesToArrayLists(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employeeArrayParser;
    }
}
