package org.bitbucket.dannyvantol.rekenlokaal.util;

public class MathEngine {
    private int minimum;
    private int maximum;

    public MathEngine(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int[] generateRandomNumbers(int times) {
        int[] numbers = new int[times];

        for (int i = 0; i < times; i++) {
            numbers[i] = (int) (Math.random() * this.maximum + this.minimum);
        }

        return numbers;
    }
}
