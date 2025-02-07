package com.design_patterns.bridge;


interface Renderer
{
    String whatToRenderAs();
}

abstract class Shape
{
    protected Renderer renderer;

    public Shape(Renderer renderer){
        this.renderer = renderer;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return "Drawing " + getName() + " as " + renderer.whatToRenderAs();
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer) {
        super(renderer);
    }
    @Override
    public String getName()
    {
        return "Triangle";
    }
}

class Square extends Shape
{
    public Square(Renderer renderer) {
        super(renderer);
    }
    @Override
    public String getName()
    {
        return "Square";
    }
}

class VectorRenderer implements Renderer{

    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements Renderer{

    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}



public class BridgeTest {
    public static void main(String[] args) {
        VectorRenderer vector = new VectorRenderer();
        RasterRenderer raster = new RasterRenderer();
        Shape triangle = new Triangle(vector);
        Shape square = new Square(raster);

        System.out.println(triangle);
        System.out.println(square);
    }

}
