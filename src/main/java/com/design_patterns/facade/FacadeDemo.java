package com.design_patterns.facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineWidth, int lineHeight) {
        this.lineWidth = lineWidth;
        characters = new char[lineWidth * lineHeight];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class ViewPort {
    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;

    public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        super();
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsetY);
    }
}

class Console {
    private List<ViewPort> viewPorts = new ArrayList<>();
    private int width, height;

    public Console(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public void addViewPort(ViewPort viewPort) {
        viewPorts.add(viewPort);
    }

    // factory method for Facade
    public static Console newConsole(int width, int height) {
        Buffer buffer = new Buffer(width, height);
        ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
        Console console = new Console(width, height);
        console.addViewPort(viewPort);
        return console;
    }

    public void render() {
        System.out.println("rendering.........");
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                for (ViewPort vp : viewPorts) {
                    System.out.print(vp.charAt(x, y));
                }
                System.out.println();
            }
        }
    }
}

public class FacadeDemo {

    public static void main(String args[]) {

        // without Facade Factory method
        Buffer buffer = new Buffer(3, 2);
        ViewPort viewPort = new ViewPort(buffer, 3, 2, 0, 0);
        Console console = new Console(3, 2);
        console.addViewPort(viewPort);
        console.render();
        // using Facade factory method
        Console newConsole = Console.newConsole(5, 6);
        newConsole.render();
    }
}