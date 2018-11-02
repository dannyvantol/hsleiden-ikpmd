package org.bitbucket.dannyvantol.rekenlokaal.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MathEngine {
    private int minimum;
    private int maximum;

    public MathEngine(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int[] generateRandomNumbers(int times, int[] excluded) {
        int[] numbers = new int[times];
        Set<Integer> forbidden = new HashSet<>();

        for (int x: excluded) {
            forbidden.add(x);
        }
        int i = 0;

        while (i < times) {
            int random =  ((int) (Math.random() * (this.maximum - this.minimum)) + 1) + this.minimum;

            if (!forbidden.contains(random) && random % this.minimum == 0) {
                numbers[i] = random;
                forbidden.add(random);
                i++;
            }
        }

        return numbers;
    }
}
