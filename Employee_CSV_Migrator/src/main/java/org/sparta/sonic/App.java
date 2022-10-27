package org.sparta.sonic;

public class App  {

    public static void main( String[] args ) {

        Starter.setExpectedEmployeeCount(ArgumentHandler.getRowCount(args));
        Starter.start();
    }


}
