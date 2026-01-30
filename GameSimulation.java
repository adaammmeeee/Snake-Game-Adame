package snippet;

public class GameSimulation {

    public static void scenario1( GameLogic gl) {
        System.out.println("Start of scenario 1");
        long start = System.nanoTime();
        for(int i=0; i<1_000_000; i++)
        {
            gl.shiftSnake();
            gl.updateHeadPosition();
            gl.hasEaten();
            if (gl.testCollision())
            {
                System.out.println("Collision, cancel");
            }
        }
        long end = System.nanoTime();
        long duration = end-start;
        System.out.println("end of scenario 1 duration : " + duration/1_000);
    }
}
