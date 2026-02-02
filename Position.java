package snippet;

public class Position {
    public int a;
    public int b;

    // Use for hashcode
    public int maxA;
    public Position(int a, int b, int maxA)
    {
        this.a = a;
        this.b = b;
        this.maxA = maxA;
    }

    public Position(Position p, int maxA)
    {
        this.a = p.a;
        this.b = p.b;
    }

    @Override
    public String toString() {
        return "(" + a + "," + b + ")";
    }

    @Override
    public boolean equals(Object b)
    {
        if(b instanceof Position)
        {
            if ( (((Position) b).a == this.a) && (((Position) b).b == this.b) )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return a + b*maxA;
    }
}
