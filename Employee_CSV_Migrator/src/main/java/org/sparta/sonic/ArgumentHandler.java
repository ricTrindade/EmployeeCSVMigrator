package org.sparta.sonic;

import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.util.logging.Level;

public class ArgumentHandler {

    // Number of rows user expects from the csv
    static int getRowCount(String[] args) {
        int rowCount = 0;
        for (String arg : args) {

            try {
                rowCount = Integer.parseInt(arg);
                if (rowCount > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                LoggerSingleton.getSingleton().getLogger().log(Level.FINE, "No parsable int in argument: " + arg);
            }
        }

        return rowCount;
    }

}
