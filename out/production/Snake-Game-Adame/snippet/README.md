# Snake-Game

Implementation of snake game with graphic interface and performances comparison 

## Graphics

Game window managed by the class `Gameplay.java`. Methods used for drawing are stored is `DrawHelper.java` 

## Game Logic

At each iteration, the snake must :  
- Move in the direction chosen by the user `shiftSnake()`
- Check if it ate food `hasEaten()`
- Check for collisions `testCollision()`
- Compute the new head position `updateHeadPosition()`

We also need to generate food `generateFood()` 

Logic is defined by the abstract class `GameLogic.java`.  
The goal is to test which implementation of this logic is the fastest.  
We tried different data structure to represent the snake (set, list and bitSet) `GameLogicSet.java`, `GameLogicList.java` and `GameLogicBit.java`. 

## Game Simulation

To do measurement we need to simulate a game, done with `GameSimulation.java`

## Result

![plot](./res_plot.png)
