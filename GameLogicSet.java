package snippet;

import java.util.*;

public class GameLogicSet extends GameLogic {
    private final List<Position> foodPos = new LinkedList<>();
    private final Set<Position> snakePosSet = new HashSet<>(x*y*2);
    private final Set<Position> availablePos = new HashSet<>(x*y*2);


    public GameLogicSet(int x, int y, int snakeLength)
    {
        super(x,y, snakeLength);
        for(int i=0; i<snakeLength; i++)
        {
            snakePosSet.add(new Position(i,0,x));
        }
        for(int i=3; i<x;i++)
        {
            for(int j=0; j<y; j++)
            {
                availablePos.add(new Position(i,j,x));
            }
        }
    }
    @Override
    public boolean shiftSnake() {
        Position headPos = new Position(newHeadPosition, x);
        snakePosList.add(headPos);
        snakePosSet.add(headPos);
        availablePos.remove(headPos);
        if(!hasEaten)
        {
            // Erase the queue, for the list and the bitset
            Position queue = snakePosList.getFirst();
            snakePosSet.remove(queue);
            availablePos.add(queue);
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
    public Position generateFood() {
        int randomIndex = seed.nextInt(availablePos.size());
        Iterator<Position> it = availablePos.iterator();
        int cpt = 0;
        while (it.hasNext())
        {
            Position food = it.next();
            if (cpt == randomIndex)
            {
                foodPos.add(food);
                return food;
            }
            cpt++;
        }
        System.out.println("End of the game gg");
        return new Position(-1,-1,x);    }

    @Override
    public boolean testCollision() {
        if (snakePosSet.contains(newHeadPosition))
        {
            collision = true;
            return true;
        }
        return false;
    }

    @Override
    public void hasEaten() {
        for(var pos : foodPos)
        {
            if(pos.equals(newHeadPosition)) {
                hasEaten = true;
                return;
            }
        }
    }
}
