package snippet;

import java.util.*;
import java.util.List;

public class GameLogicBit extends GameLogic {

    private final BitSet foodPos;
    private final BitSet snakePosBit;

    public GameLogicBit(int x, int y, int snakeLength)
    {
        super(x,y,snakeLength);

        foodPos = new BitSet(x*y);
        snakePosBit = new BitSet(x*y);

        for(int i=0; i<snakeLength; i++)
        {
            snakePosBit.set(i);
        }
    }

    ///////////////////// COORDINATES CONVERSION ////////////////////////


    /** Convert a position (a,b) to a bitSet of size x*y
     * bit at position (a,b) is set to true
     * other bits are set to false */
    public BitSet positionToBitSet(Position p)
    {
        BitSet b = new BitSet(x*y);
        int pos = p.a +  p.b *x;
        b.set(pos);
        return b;
    }

    /** Convert a position (a,b) to an index
    * corresponding to the position in a grid x*y */
    public int positionToBitIndex(Position p)
    {
        return p.a +  p.b *x;
    }

    /** Convert an index (from a BitSet) to its corresponding position in a grid */
    public Position bitToPosition(int bitIndex)
    {
        return new Position(bitIndex%x, bitIndex/x, x);
    }


    ////////////////////////// MOVEMENT /////////////////////////


    /** Shift the snake
    * return true if it's a classic shift.
    * Return false if the snake became bigger => the queue is not erased
     */
    @Override
    public boolean shiftSnake()
    {
        snakePosList.add(new Position(newHeadPosition,x));
        snakePosBit.set(positionToBitIndex(newHeadPosition));
        if(!hasEaten)
        {
            // Erase the queue, for the list and the bitset
            Position queue = snakePosList.getFirst();
            snakePosBit.set(positionToBitIndex(queue),false);
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

    ///////////////////// FOOD GENERATION //////////////////////////

    /** Randomly generate food on the grid,
     *  Update the foodPos bitset and return the position of the food generated */
    @Override
    public Position generateFood()
    {
        // Get all the bits available on the grid
        BitSet b = (BitSet) snakePosBit.clone();
        b.flip(0,x*y);

        // Put all the 1 bit in a list to pick them randomly
        List<Integer> magicBag = new ArrayList<>();
        for(int i=0; i<x*y; i++)
        {
            if(b.get(i))
            {
                magicBag.add(i);
            }
        }

        int randomPos = seed.nextInt(magicBag.size());
        int randomBitIndex = magicBag.get(randomPos);

        foodPos.set(randomBitIndex);
        return bitToPosition(randomBitIndex);
    }

    ///////////////////// VERIFICATIONS AT EACH ITERATION OF THE GAME /////////////////////

    /** Set the attribute collision to true if the snake head touched its body */
    @Override
    public boolean testCollision() {
        int newHeadPos = positionToBitIndex(newHeadPosition);
        if (snakePosBit.get(newHeadPos))
        {
            collision = true;
            return true;
        }
        return false;
    }

    /**
     * Set the attribute hasEaten to true if the snake head touched an food
     */
    @Override
    public void hasEaten() {
        int headPosBitIndex = positionToBitIndex(newHeadPosition);
        if (foodPos.get(headPosBitIndex))
        {
            hasEaten = true;
            foodPos.set(headPosBitIndex, false);
        }
    }
}
