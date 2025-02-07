package com.design_patterns.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class LineToPointAdapter extends ArrayList<Point>
{
  private static int count = 0;

  public LineToPointAdapter(Line line)
  {
    System.out.println(
      String.format("%d: Generating points for line [%d,%d]-[%d,%d] (no caching)",
        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

    int left = Math.min(line.start.x, line.end.x);
    int right = Math.max(line.start.x, line.end.x);
    int top = Math.min(line.start.y, line.end.y);
    int bottom = Math.max(line.start.y, line.end.y);
    int dx = right - left;
    int dy = line.end.y - line.start.y;

    if (dx == 0)
    {
      for (int y = top; y <= bottom; ++y)
      {
        add(new Point(left, y));
      }
    }
    else if (dy == 0)
    {
      for (int x = left; x <= right; ++x)
      {
        add(new Point(x, top));
      }
    }
  }
}

class AdapterDemo
{
  private static final List<VectorObject> vectorObjects =
    new ArrayList<>(Arrays.asList(
      new VectorRectangle(1,1,10,10),
      new VectorRectangle(3,3,6,6)
    ));

  public static void drawPoint(Point p)
  {
    System.out.print(".");
  }

  private static void draw()
  {
    for (VectorObject vo : vectorObjects)
    {
      for (Line line : vo)
      {
        LineToPointAdapter adapter = new LineToPointAdapter(line);
        adapter.forEach(AdapterDemoCache::drawPoint);
      }
    }
  }

  public static void main(String[] args)
  {
    draw();
//    draw();
  }
}
