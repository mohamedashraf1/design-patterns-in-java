package com.design_patterns.bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface MyRenderer
{
  void renderCircle(float radius);
}

class MyVectorRenderer implements MyRenderer
{
  @Override
  public void renderCircle(float radius)
  {
    System.out.println("Drawing a circle of radius " + radius);
  }
}

class MyRasterRenderer implements MyRenderer
{
  @Override
  public void renderCircle(float radius)
  {
    System.out.println("Drawing pixels for a circle of radius " + radius);
  }
}

abstract class MyShape
{
  protected MyRenderer renderer;

  public MyShape(MyRenderer renderer)
  {
    this.renderer = renderer;
  }

  public abstract void draw();
  public abstract void resize(float factor);
}

class Circle extends MyShape
{
  public float radius;

  @Inject
  public Circle(MyRenderer renderer)
  {
    super(renderer);
  }

  public Circle(MyRenderer renderer, float radius)
  {
    super(renderer);
    this.radius = radius;
  }

  @Override
  public void draw()
  {
    renderer.renderCircle(radius);
  }

  @Override
  public void resize(float factor)
  {
    radius *= factor;
  }
}

class ShapeModule extends AbstractModule
{
  @Override
  protected void configure()
  {
    bind(MyRenderer.class).to(MyVectorRenderer.class);
  }
}

class BridgeDemo
{
  public static void main(String[] args)
  {
    MyRasterRenderer rasterRenderer = new MyRasterRenderer();
    MyVectorRenderer vectorRenderer = new MyVectorRenderer();
    Circle circle = new Circle(vectorRenderer, 5);
    circle.draw();
    circle.resize(2);
    circle.draw();

    Injector injector = Guice.createInjector(new ShapeModule());
    Circle instance = injector.getInstance(Circle.class);
    instance.radius = 3;
    instance.draw();
    instance.resize(2);
    instance.draw();
  }
}
