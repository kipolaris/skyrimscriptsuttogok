package game.model.logging;

import game.model.main.GameMain;

import java.util.HashSet;
import java.util.Set;


import java.util.Queue;
import java.util.LinkedList;

public class Suttogo {
    private Suttogo() {}

    public enum Level {
        INFO, ERROR, NONE, NOTE
    }
    private static Level level = Level.NOTE;
    private static final Set<String> enabledClasses = new HashSet<>();
    private static final Queue<String> messageQueue = new LinkedList<>();

    public static void setLevel(Level newLevel) {
        level = newLevel;
    }

    public static void info(String message) {
        if(level == Level.INFO || level == Level.ERROR)
                log("INFO "+message);
    }

    public static void error(String message) {
        if(level == Level.ERROR || level == Level.NOTE || level == Level.INFO){
            String loggable = "ERROR "+message;
            GameMain.lastOutput = GameMain.lastOutput + loggable + '\n';
            log(loggable);
        }
    }

    public static void note(String message){
        if(level == Level.NOTE || level == Level.ERROR) log("NOTE "+message);
    }

    private static void log(String message) {
        System.out.print(message+'\n');
    }
}
