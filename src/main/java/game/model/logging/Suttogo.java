package game.model.logging;

import game.model.main.GameMain;

import java.util.HashSet;
import java.util.Set;


import java.util.Queue;
import java.util.LinkedList;

import java.util.EnumMap;

import java.util.EnumMap;

/** Class for console output */
public class Suttogo {
    public Suttogo() {}

    /**
     * Enum representing the logging levels.
     */
    public enum Level {
        NONE, INFO, NOTE, ERROR
    }

    private static Level level = Level.INFO;

    private static String lastMessage = null;
    public String getLastMessage() { return lastMessage; }

    /**
     * Method to set the logging level.
     *
     * @param newLevel the new logging level
     */
    public static void setLevel(Level newLevel) {
        level = newLevel;
    }

    /**
     * Logs an informational message. This is intended for the user.
     *
     * @param message the message
     */
    public static void info(String message) {
        if (shouldLog(Level.INFO)) {
            log("INFO " + message);
            lastMessage = message;
        }
    }

    /**
     * Logs an error message.
     *
     * @param message the message
     */
    public static void error(String message) {
        if (shouldLog(Level.ERROR)) {
            String loggable = "ERROR " + message;
            GameMain.lastOutput = GameMain.lastOutput + loggable + '\n';
            log(loggable);
            lastMessage = message;
        }
    }

    /**
     * Logs a note message. This is for debugging purposes and not intended for the user.
     *
     * @param message the message
     */
    public static void note(String message) {
        if (shouldLog(Level.NOTE)) {
            log("NOTE " + message);
            lastMessage = message;
        }
    }

    /**
     * Checks if a message should be logged based on the current logging level.
     *
     * @param messageLevel the level of the message
     * @return true if the message should be logged, false otherwise
     */
    private static boolean shouldLog(Level messageLevel) {
        EnumMap<Level, Integer> levelPriority = new EnumMap<>(Level.class);
        levelPriority.put(Level.NONE, 0);
        levelPriority.put(Level.INFO, 1);
        levelPriority.put(Level.NOTE, 2);
        levelPriority.put(Level.ERROR, 3);

        return levelPriority.get(messageLevel) >= levelPriority.get(level);
    }

    /**
     * Logs a message.
     *
     * @param message the message
     */
    private static void log(String message) {
        System.out.print(message + '\n');
    }
}


