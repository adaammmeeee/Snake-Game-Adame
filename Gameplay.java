package snippet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener
{
	private int snakeLength = 3;
	private final int x;
	private final int y;

	private GameLogic gl;

	// Constants for printing
	private final int topBorder = 4;
	private final int botBorder = 1;
	private final int rightBorder = 1;
	private final int leftBorder = 1;

	private final int pixelSize = 25;

    private final DrawHelper drawHelper;

	private boolean firstPaint = true;

	// Time attribute
    private Timer timer;
    private final int delay = 100;

    private int score = 0;

	public Gameplay(int x, int y, GameLogic gl)
	{
		this.x = x;
		this.y = y;
		drawHelper = new DrawHelper(x,y, topBorder, botBorder, rightBorder, leftBorder, pixelSize);
		this.gl = gl;
		reset();
		// Game settings
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
	}

	public void paint (Graphics g)
	{
		drawHelper.drawMenu(g, score, snakeLength);
		if (firstPaint)
		{
			// First paint of the snake
			drawHelper.drawBackground(g);
			drawHelper.drawSnake(g, gl.getSnakePosList());
			// drawing enemy
			Position enemyPos = gl.generateFood();
			drawHelper.drawPixel(enemyPos.x, enemyPos.y,g,Color.red);
			firstPaint = false;
		}
		else {
			// We shift the snake
			// We erase the queue
			Position queue = gl.getSnakePosList().getFirst();
			drawHelper.drawPixel(queue.x, queue.y, g, Color.black);
			if (!gl.shiftSnake())
			{
				// we redraw the queue
				queue = gl.getSnakePosList().getFirst();
				drawHelper.drawPixel(queue.x, queue.y, g, Color.green);
				// Paint the new food
				Position enemyPos = gl.generateFood();
				drawHelper.drawPixel(enemyPos.x, enemyPos.y,g,Color.red);
				score++;
				snakeLength++;
			}
			// reDraw the head (shift effect)
			Position headPos = gl.getSnakePosList().getLast();
			drawHelper.drawPixel(headPos.x, headPos.y, g, Color.green);

			if(gl.getCollision())
			{
				reset();
			}

        }
        g.dispose();
	}
	@Override
    public void keyTyped(KeyEvent ke) {
      
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
				if (gl.getCurrentDirection() != GameLogicBit.Direction.LEFT)
					gl.setCurrentDirection(GameLogicBit.Direction.RIGHT);
				break;

			case KeyEvent.VK_LEFT:
				if (gl.getCurrentDirection() != GameLogic.Direction.RIGHT)
					gl.setCurrentDirection(GameLogic.Direction.LEFT);
				break;

			case KeyEvent.VK_UP:
				if (gl.getCurrentDirection() != GameLogic.Direction.DOWN)
					gl.setCurrentDirection(GameLogicBit.Direction.UP);
				break;

			case KeyEvent.VK_DOWN:
				if (gl.getCurrentDirection() != GameLogic.Direction.UP)
					gl.setCurrentDirection(GameLogic.Direction.DOWN);
				break;
			case KeyEvent.VK_SPACE:
				reset();
				break;
		}
    }



	@Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    	timer.start();
		gl.updateHeadPosition();
		gl.hasEaten();
		gl.testCollision();
        repaint();
	}

	private void reset()
	{
		// Call the gc ?
		firstPaint = true;
		snakeLength = 3;
		gl = new GameLogicBit(x,y,snakeLength);
		score = 0;
		gl.setCurrentDirection(GameLogicBit.Direction.RIGHT);
	}

	public int getWidth() {
		return x * pixelSize + rightBorder * pixelSize + leftBorder * pixelSize;
	}

	public int getHeight() {
		return y * pixelSize + topBorder * pixelSize + botBorder * pixelSize;
	}
}




















