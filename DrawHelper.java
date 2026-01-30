package snippet;

import java.awt.*;
import java.util.LinkedList;

public class DrawHelper {
    private int x;
    private int y;

    private final int topBorder;
    private final int botBorder;
    private final int rightBorder;
    private final int leftBorder;

    // Constants for printing
    private final int pixelSize;

    public DrawHelper(int x, int y, int topBorder, int botBorder, int rightBorder, int leftBorder, int pixelSize){
        this.x = x;
        this.y = y;
        this.topBorder = topBorder;
        this.botBorder = botBorder;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.pixelSize = pixelSize;
    }

    public void drawBackground(Graphics g)
    {
        int width     = x*pixelSize + rightBorder*pixelSize + leftBorder*pixelSize;
        int height    = y*pixelSize + topBorder*pixelSize + botBorder*pixelSize;
        int widthNoBorder = width - rightBorder * pixelSize - leftBorder * pixelSize;
        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(leftBorder*pixelSize, topBorder*pixelSize, widthNoBorder, height - topBorder*pixelSize - botBorder*pixelSize);
    }

    public void drawMenu(Graphics g, int score, int snakeLength)
    {
        int width     = x*pixelSize + rightBorder*pixelSize + leftBorder*pixelSize;
        int height    = y*pixelSize + topBorder*pixelSize + botBorder*pixelSize;
        int widthNoBorder = width - rightBorder * pixelSize - leftBorder * pixelSize;

        g.setColor(Color.WHITE);
        g.drawRect(leftBorder*pixelSize-1, topBorder*pixelSize-1, widthNoBorder+2, height - topBorder*pixelSize - botBorder*pixelSize + 2);

        //draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(leftBorder*pixelSize, pixelSize, widthNoBorder, (topBorder-2) *pixelSize);
        g.setColor(Color.black);
        g.fillRect(leftBorder*pixelSize+1, pixelSize+1, widthNoBorder-2, (topBorder-2) *pixelSize - 2);

        //draw the title image
        g.setColor(Color.RED);
        g.setFont(new Font("arial",Font.PLAIN, 25));
        g.drawString("Snake Game", leftBorder*pixelSize+1, pixelSize*2);

        // draw scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN, 14));
        g.drawString("Scores: "+ score, leftBorder*pixelSize + (x-3)*pixelSize, pixelSize + 20);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN, 14));
        g.drawString("Length: "+ snakeLength, leftBorder*pixelSize + (x-3)*pixelSize , pixelSize + 40);
    }


    public void drawPixel(int x, int y, Graphics g, Color c)
    {
        g.setColor(c);
        g.fillRect((x+leftBorder)*pixelSize,(y+topBorder)*pixelSize,pixelSize,pixelSize);
    }


    public void drawSnake(Graphics g, LinkedList<Position> snakePosList)
    {
        // Drawing the body
        for(var bodyPosition : snakePosList)
        {
            drawPixel(bodyPosition.x,bodyPosition.y,g,Color.green);
        }
    }

}
