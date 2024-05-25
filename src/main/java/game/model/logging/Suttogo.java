package game.model.logging;

import game.model.main.GameMain;

import java.util.HashSet;
import java.util.Set;


import java.util.Queue;
import java.util.LinkedList;

/** Class for console output */
public class Suttogo {
    private Suttogo() {}

    /**
     * Enum representing the logging levels.
     */
    public enum Level {
        NONE, ERROR, NOTE, INFO
    }

    private static Level level = Level.NOTE;

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
        if (level.ordinal() >= Level.INFO.ordinal()) {
            log("INFO " + message);
        }
    }

    /**
     * Logs an error message.
     *
     * @param message the message
     */
    public static void error(String message) {
        if (level.ordinal() >= Level.ERROR.ordinal()) {
            String loggable = "ERROR " + message;
            GameMain.lastOutput = GameMain.lastOutput + loggable + '\n';
            log(loggable);
        }
    }

    /**
     * Logs a note message. This is for debugging purposes and not intended for the user.
     *
     * @param message the message
     */
    public static void note(String message) {
        if (level.ordinal() >= Level.NOTE.ordinal()) {
            log("NOTE " + message);
        }
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

