package snippet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameSimulation {
    private final ArrayList<GameLogic> logics = new ArrayList<>();


    public GameSimulation(int logicType)
    {
        int[] dimensions = {10, 100, 1000, 10000, 100000};
        int[] snakeLengths = {3, 30, 300, 3000, 30000};
        int parameters = 3;
        switch (logicType)
        {
            case 0:
                System.out.println("Benchmarking bits implementation");
                for(int i = 0; i< parameters; i++)
                {
                    logics.add(new GameLogicBit(dimensions[i], dimensions[i], snakeLengths[i]));
                }
                break;
            case 1:
                System.out.println("Benchmarking set implementation");
                for(int i = 0; i< parameters; i++)
                {
                    logics.add(new GameLogicSet(dimensions[i], dimensions[i], snakeLengths[i]));
                }
                break;
            case 2:
                System.out.println("Benchmarking list implementation");
                for(int i = 0; i< parameters; i++)
                {
                    logics.add(new GameLogicList(dimensions[i], dimensions[i], snakeLengths[i]));
                }
                break;


        }
    }

    public void scenario() {
        int cpt = 0;

        long startShift;
        long startHasEaten;
        long startTestCollision;

        long endShift;
        long endHasEaten;
        long endTestCollision;

        long totalShift = 0;
        long totalHasEaten = 0;
        long totalTestCollision = 0;

        for(var gl : logics)
        {
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
            System.out.println("Start of scenario " + cpt);

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
            System.out.println("scenario " + cpt + " duration method shiftSnake    : " + totalShift);
            System.out.println("scenario " + cpt + " duration method hasEaten      : " + totalHasEaten);
            System.out.println("scenario " + cpt + " duration method TestCollision : " + totalTestCollision);


            cpt++;
        }
    }
}
