package org.sparta.sonic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void read(){

    }

    public static String[] connectToFile(String filename){

        String[] strArray = new String[10002];

        String line = "";
        try (FileReader fileReader = new FileReader(filename)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i=0;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
                strArray[i++] = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strArray;
    }
}
