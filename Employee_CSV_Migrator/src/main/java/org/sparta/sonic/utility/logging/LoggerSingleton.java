package org.sparta.sonic.utility.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSingleton {
    private static final LoggerSingleton singleton = new LoggerSingleton();
    private Logger logger;

    private LoggerSingleton()
    {
        initLogger();
    }

    public static LoggerSingleton getSingleton()
    {
        return singleton;
    }

    public Logger getLogger()
    {
        return logger;
    }

    private void initLogger()
    {
        logger = Logger.getLogger("migrator-logger");
        CustomLoggerConfiguration.configureLogger(logger, Level.OFF);
    }

}
