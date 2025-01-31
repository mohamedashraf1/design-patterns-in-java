package com.design_patterns.prototype;


class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other){
        this(other.x, other.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line(Line other){
        this(new Point(other.start), new Point(other.end));
    }

    public Line deepCopy()
    {
        return new Line(this);
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

public class PrototypeTestCopyConstructor {
    public static void main(String[] args) throws Exception {
        Point start = new Point(1, 1);
        Point end = new Point(2, 2);
        Line line = new Line(start, end);

        // using copy constructor
        System.out.println("Line before: " + line);
        System.out.println(line);
        Line copy = line.deepCopy();
        copy.start.x = 5;
        copy.end.y = 10;
        System.out.println("Line after: " + line);
        System.out.println("Copy: " + copy);

    }


}
