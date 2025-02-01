package com.design_patterns.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class MyClass {
    private String name;
    public int age;

    private MyClass() {
        this.name = "Default";
        this.age = 0;
    }

    private MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    private void secretMethod() {
        System.out.println("This is a private method!");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // Get the Class object for MyClass
        Class<?> myClass = Class.forName("com.design_patterns.reflection.MyClass");

        // Inspect constructors
        System.out.println("Constructors:");
        for (Constructor<?> constructor : myClass.getDeclaredConstructors()) {
            System.out.println(constructor);
        }

        // Create an instance of MyClass using the no-arg constructor
        Constructor<?> noArgConstructor = myClass.getDeclaredConstructor();
        noArgConstructor.setAccessible(true);
        Object myObject = noArgConstructor.newInstance();

        // Access and modify public fields
        Field ageField = myClass.getField("age");
        ageField.set(myObject, 25); // Set age to 25
        System.out.println("Age: " + ageField.get(myObject)); // Get age

        // Access and modify private fields
        Field nameField = myClass.getDeclaredField("name");
        nameField.setAccessible(true); // Allow access to private field
        nameField.set(myObject, "John"); // Set name to John
        System.out.println("Name: " + nameField.get(myObject)); // Get name

        // Invoke public methods
        Method displayMethod = myClass.getMethod("display");
        displayMethod.invoke(myObject); // Call display()

        // Invoke private methods
        Method secretMethod = myClass.getDeclaredMethod("secretMethod");
        secretMethod.setAccessible(true); // Allow access to private method
        secretMethod.invoke(myObject); // Call secretMethod()
    }
}