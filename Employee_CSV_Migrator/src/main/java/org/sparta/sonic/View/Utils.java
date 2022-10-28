package org.sparta.sonic.View;

import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();
    public static int scannedInteger() {
        Scanner intScanner = new java.util.Scanner(System.in);
        logger.log(Level.FINEST, "Scanner has started");
        int scannedInt;
        scannedInt = getScannedInt(intScanner);
        while (scannedInt < 1 || scannedInt > 2){
            scannedInt =getScannedInt(intScanner);
        }
        return scannedInt;
    }

    private static int getScannedInt(Scanner intScanner) {

        int scannedInt;
        while (!intScanner.hasNextInt()) {
            intScanner.nextLine();
        }
        scannedInt = intScanner.nextInt();
        return scannedInt;
    }
}
