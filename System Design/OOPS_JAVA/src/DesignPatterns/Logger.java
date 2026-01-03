package DesignPatterns;

public class Logger {
    private static Logger logger = null;
    private Logger() {}


    // Double checking for multithreading
    public static Logger getLogger() {
        // problem was unnecessary blocking once logger is created so, double checking.
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) { // what if A & B who were waiting also start to create the instance
                    logger = new Logger();
                }
            }}
        return logger;
    }
}
