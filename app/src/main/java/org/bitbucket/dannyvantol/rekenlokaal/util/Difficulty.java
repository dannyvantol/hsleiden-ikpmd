package org.bitbucket.dannyvantol.rekenlokaal.util;

public enum Difficulty {
    EASY(0),
    NORMAL(1),
    HARD(2);

    private int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
