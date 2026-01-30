package snippet;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Position(Position p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
