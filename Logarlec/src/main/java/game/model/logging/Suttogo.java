package game.model.logging;

import java.util.HashSet;
import java.util.Set;


import java.util.Queue;
import java.util.LinkedList;

public class Suttogo {
    private Suttogo() {}

    public enum Level {
        INFO, ERROR, NONE
    }
    private static Level level = Level.INFO;
    private static final Set<String> enabledClasses = new HashSet<>();
    private static final Queue<String> messageQueue = new LinkedList<>();

    public static void setLevel(Level newLevel) {
        level = newLevel;
    }

    public static void info(String message) {
        log(message);
    }

    public static void error(String message) {
        log(message);
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
