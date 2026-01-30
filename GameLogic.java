package snippet;

import java.util.LinkedList;
import java.util.Random;

public abstract class GameLogic {
    protected int x,y, snakeLength;

    protected boolean hasEaten = false;
    protected boolean collision = false;

    protected final LinkedList<Position> snakePosList = new LinkedList<>();

    protected Direction currentDirection = Direction.RIGHT;
    protected Position newHeadPosition;

    protected Random seed = new Random();


    public enum Direction {
        UP,
        RIGHT,
        LEFT,
        DOWN
    }

    public GameLogic(int x, int y, int snakeLength)
    {
        this.x = x;
        this.y = y;
        this.snakeLength = snakeLength;

        for(int i=0; i<snakeLength; i++)
        {
            Position buffer = new Position(i,0);
            snakePosList.add(buffer);
        }

        newHeadPosition = new Position(snakeLength,0);

    }


    /** Shift the snake
     * return true if it's a classic shift.
     * Return false if the snake became bigger => the queue is not erased
     */
    public abstract boolean shiftSnake();

    /** Update the newHeadPosition var depending on the currentDirection */
    public void updateHeadPosition()
    {
        Position headPos = snakePosList.getLast();
        newHeadPosition.x = headPos.x;
        newHeadPosition.y = headPos.y;
        switch (currentDirection) {
            case UP -> newHeadPosition.y    = Math.floorMod(newHeadPosition.y-1, y);
            case DOWN -> newHeadPosition.y  = Math.floorMod(newHeadPosition.y+1, y) ;
            case RIGHT -> newHeadPosition.x = Math.floorMod(newHeadPosition.x+1, x);
            case LEFT -> newHeadPosition.x  = Math.floorMod(newHeadPosition.x-1, x);
        }
    }

    /** Randomly generate food on the grid and return the position of the food generated */
    public abstract Position generateFood();

    /** Set the attribute collision to true if the snake head touched its body */
    public abstract boolean testCollision();

    /**
     * Set the attribute hasEaten to true if the snake head touched an food
     */
    public abstract void hasEaten();

    ///////////////////// GETTERS and SETTERS ////////////////////////

    public void setCurrentDirection(Direction currentDirection)
    {
        this.currentDirection = currentDirection;
    }

    public Direction getCurrentDirection()
    {
        return this.currentDirection;
    }

    public LinkedList<Position> getSnakePosList()
    {
        return this.snakePosList;
    }

    public boolean getCollision() {
        return collision;
    }

}
