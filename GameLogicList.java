package snippet;

import java.util.*;

public class GameLogicList extends GameLogic {
    private final List<Position> foodPos = new LinkedList<>();
    private final Set<Position> availablePos = new HashSet<>(x*y);

    public GameLogicList(int x, int y, int snakeLength)
    {
        super(x,y, snakeLength);
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
        availablePos.remove(headPos);
        if(!hasEaten)
        {
            // Erase the queue, for the list
            Position queue = snakePosList.getFirst();
            snakePosList.removeFirst();
            availablePos.add(queue);
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
            if (cpt == randomIndex)
            {
                foodPos.add(it.next());
                return it.next();
            }
        }
        System.out.println("End of the game gg");
        return new Position(-1,-1,x);
    }

    @Override
    public boolean testCollision() {
        Position head = snakePosList.getFirst();
        Iterator<Position> it = snakePosList.iterator();
        it.next();
        while (it.hasNext())
        {
            if(it.next().equals(head))
            {
                collision = true;
                return true;
            }
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
