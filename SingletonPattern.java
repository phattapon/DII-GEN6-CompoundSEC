package com.fitnessaccesscontrol.pattern;

public class SingletonPattern {
    private static SingletonPattern instance;

    private SingletonPattern() {
        // Private constructor
    }

    public static SingletonPattern getInstance() {
        if (instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }
}
