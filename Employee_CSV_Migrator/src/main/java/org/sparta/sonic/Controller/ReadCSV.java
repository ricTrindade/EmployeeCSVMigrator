package org.sparta.sonic.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {

    public static String[] connectToFile(String filename, int fileLength){

        String[] strArray = new String[fileLength];

        String line;
        try (FileReader fileReader = new FileReader(System.getProperty("user.dir") + filename)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i=0;
            while((line = bufferedReader.readLine()) != null) {
                strArray[i++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strArray;
    }
}
