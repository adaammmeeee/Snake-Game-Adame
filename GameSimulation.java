package snippet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameSimulation {

    public static void scenario(GameLogic gl) {
        long start;
        long end;
        long total;
        // Impossible to reach this food
        gl.snakePosList.add(new Position(0,1, gl.x));

        // Warmup part
        for(int i=0; i<100_000; i++)
        {
            gl.shiftSnake();
            gl.updateHeadPosition();
            gl.hasEaten();
            if (gl.hasEaten)
            {
                System.out.println("Not supposed to eat, cancel");
            }
            if (gl.testCollision())
            {
                System.out.println("Collision, cancel");
            }
        }
        System.out.println("Start of scenario with grid size " + gl.x + "x" + gl.y + " and a length snake : " + gl.snakeLength);
        // Measure time
        start = System.nanoTime();
        for(int i=0; i<100_000; i++)
        {
            gl.shiftSnake();
            gl.updateHeadPosition();
            gl.hasEaten();
            gl.testCollision();
        }
        end = System.nanoTime();
        total = end-start;
        System.out.println("Duration : " + total);
    }
}
