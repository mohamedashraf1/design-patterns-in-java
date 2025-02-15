package com.design_patterns.decorator;

import java.util.function.Supplier;


// we are NOT altering the base class of these objects
// cannot make ColoredSquare, ColoredCircle

class ColoredShape1<T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape1(Supplier<? extends T> ctor, String color) {
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape1<T extends Shape> implements Shape
{
    private Shape shape;
    private int transparency;

    public TransparentShape1(Supplier<? extends T> ctor, int transparency)
    {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}

class StaticDecoratorDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape1<Square> blueSquare = new ColoredShape1<>(() -> new Square(20), "blue");
        System.out.println(blueSquare.info());

        // ugly!
        TransparentShape1<ColoredShape1<Circle>> myCircle =
          new TransparentShape1<>(() ->
              new ColoredShape1<>(() -> new Circle(5), "green"), 50
          );
        System.out.println(myCircle.info());
        // cannot call myCircle.resize()
    }
}
