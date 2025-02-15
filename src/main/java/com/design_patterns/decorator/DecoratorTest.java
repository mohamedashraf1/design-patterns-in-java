package com.design_patterns.decorator;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

interface BirdAndLizard{
    Bird bird = new Bird();
    Lizard lizard = new Lizard();
}

class Dragon implements BirdAndLizard
{
    private int age;
    public void setAge(int age)
    {
        bird.age = age;
        lizard.age = age;
        this.age = age;
    }
    public String fly()
    {
        return bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}
public class DecoratorTest {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.setAge(1);
        System.out.println(dragon.crawl());
        System.out.println(dragon.fly());
    }
}
