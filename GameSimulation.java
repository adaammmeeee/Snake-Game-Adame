package snippet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameSimulation {

    public static void scenario(GameLogic gl) {
        long startShift;
        long startHasEaten;
        long startTestCollision;

        long endShift;
        long endHasEaten;
        long endTestCollision;

        long totalShift = 0;
        long totalHasEaten = 0;
        long totalTestCollision = 0;
        // Warmup part
        for(int i=0; i<100_000; i++)
        {
            gl.shiftSnake();
            gl.updateHeadPosition();
            gl.hasEaten();
            if (gl.testCollision())
            {
                System.out.println("Collision, cancel");
            }
        }
        System.out.println("Start of scenario with grid size " + gl.x + "x" + gl.y + " and a length snake : " + gl.snakeLength);
        // Measure time
        for(int i=0; i<100_000; i++)
        {
            startShift = System.nanoTime();
            gl.shiftSnake();
            endShift = System.nanoTime();
            totalShift += endShift-startShift;

            gl.updateHeadPosition();

            startHasEaten = System.nanoTime();
            gl.hasEaten();
            endHasEaten = System.nanoTime();
            totalHasEaten += endHasEaten-startHasEaten;

            startTestCollision = System.nanoTime();
            gl.testCollision();
            endTestCollision = System.nanoTime();
            totalTestCollision += endTestCollision-startTestCollision;

        }
            System.out.println("Duration method shiftSnake    : " + totalShift);
            System.out.println("Duration method hasEaten      : " + totalHasEaten);
            System.out.println("Duration method TestCollision : " + totalTestCollision);
    }
}
