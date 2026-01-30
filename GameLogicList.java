package snippet;

public class GameLogicList extends GameLogic {


    public GameLogicList(int x, int y, int snakeLength)
    {
        super(x,y, snakeLength);

    }

    @Override
    public boolean shiftSnake() {
        snakePosList.add(new Position(newHeadPosition));
        if(!hasEaten)
        {
            // Erase the queue, for the list and the bitset
            Position queue = snakePosList.getFirst();
            snakePosList.removeFirst();
            return true;
        }
        else
        {
            // Else no need to erase the queue, the snake ate and is bigger
            hasEaten = false;
            return false;
        }
    }

    @Override
    public void updateHeadPosition() {

    }

    @Override
    public Position generateFood() {
        return null;
    }

    @Override
    public boolean testCollision() {
        return false;
    }

    @Override
    public void hasEaten() {

    }
}
