package model;

import android.graphics.Point;

public class CheckerMove
{
    private final Point from;
    private final Point to;
    char dir;
    
    public CheckerMove(Point from2, char dir, android.graphics.Point to2)
    {
        this.from = from2;
        this.dir = dir;
        this.to = to2;
    }

    public Point getFrom()
    {
        return from;
    }

    public char getDirection()
    {
        return dir;
    }

    public Point getTo()
    {
        return to;
    }
}
