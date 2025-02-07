package com.design_patterns.factory;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonFactory
{
    private static int idCount = 0;
    public Person createPerson(String name)
    {
        return new Person(idCount++, name);
    }

}
public class FactoryTest {
    public static void main(String[] args) {
        PersonFactory factory = new PersonFactory();
        Person first = factory.createPerson("Mohamed");
        Person second = factory.createPerson("Ashraf");
        System.out.println(first);
        System.out.println(second);
    }
}
