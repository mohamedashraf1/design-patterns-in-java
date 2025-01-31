package com.design_patterns.prototype;


import java.io.*;

class Point2 implements Serializable
{
    public int x, y;

    public Point2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line2 implements Serializable
{
    public Point2 start, end;

    public Line2(Point2 start, Point2 end)
    {
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

public class PrototypeTestSerialization {
    public static void main(String[] args) throws Exception {
        Point2 start = new Point2(1, 1);
        Point2 end = new Point2(2, 2);
        Line2 line = new Line2(start, end);

        System.out.println("Line before serialization: " + line);
        // serialize Object
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(line);
        byte[] serializedLine = byteArrayOutputStream.toByteArray();
        // deserialize it back
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedLine);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Line2 copy = (Line2) objectInputStream.readObject();
        copy.start.x = 100;
        copy.end.y = 200;
        System.out.println("Line after serialization: " + line);
        System.out.println("Copy after serialization: " + copy);
    }


}
