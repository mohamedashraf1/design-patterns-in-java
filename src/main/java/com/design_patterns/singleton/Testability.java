package com.design_patterns.singleton;


import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

interface Database
{
  int getPopulation(String name);
}



class SingletonDatabase
{
  private Dictionary<String, Integer> capitals = new Hashtable<>();
  private static int instanceCount = 0;
  public static int getCount() { return instanceCount; }

  private SingletonDatabase()
  {
    instanceCount++;
    System.out.println("Initializing database");

    try {
      Path fullPath = Paths.get("H:\\Courses\\Design patterns\\design-patterns-in-java\\src\\main\\java\\com\\design_patterns\\singleton\\capitals.txt");
      List<String> lines = Files.readAllLines(fullPath);

      for(int i = 0 ; i <= lines.size()-2 ; i+=2){
          capitals.put(lines.get(i).trim(), Integer.parseInt(lines.get(i+1)));
      }

    }
    catch (Exception e)
    {
      // handle it!
      System.err.println(e);
    }
  }

  private static final SingletonDatabase INSTANCE = new SingletonDatabase();

  public static SingletonDatabase getInstance()
  {
    return INSTANCE;
  }

  public int getPopulation(String name)
  {
    return capitals.get(name);
  }
}

class SingletonRecordFinder
{
  public int getTotalPopulation(List<String> names)
  {
    int result = 0;
    for (String name : names)
      result += SingletonDatabase.getInstance().getPopulation(name);
    return result;
  }
}

class ConfigurableRecordFinder
{
  private Database database;

  public ConfigurableRecordFinder(Database database) {
    this.database = database;
  }

  public int getTotalPopulation(List<String> names)
  {
    int result = 0;
    for (String name : names)
      result += database.getPopulation(name);
    return result;
  }
}

class SingletonTestabilityDemo
{
  public static void main(String[] args) {
    SingletonDatabase db = SingletonDatabase.getInstance();

    String city = "Tokyo";
    int pop = db.getPopulation(city);
    System.out.println(
      String.format("%s has population %d", city, pop)
    );
  }
}

class DummyDatabase implements Database
{
  private Dictionary<String, Integer> data = new Hashtable<>();

  public DummyDatabase() {
    data.put("alpha", 1);
    data.put("beta", 2);
    data.put("gamma", 3);
  }

  @Override
  public int getPopulation(String name)
  {
    return data.get(name);
  }
}

public class Testability
{
  @Test
  public void isSingletonTest()
  {
    SingletonDatabase db = SingletonDatabase.getInstance();
    SingletonDatabase db2 = SingletonDatabase.getInstance();
    assertSame(db, db2);
    assertEquals(1, SingletonDatabase.getCount());
  }

  @Test
  public void singletonTotalPopulationTest()
  {
    // testing on a live database
    SingletonRecordFinder rf = new SingletonRecordFinder();
    List<String> names = List.of("Seoul", "Mexico City");
    int tp = rf.getTotalPopulation(names);
    assertEquals(17500000+17400000, tp);
  }

  @Test
  public void dependentPopulationTest()
  {
    DummyDatabase db = new DummyDatabase();
    ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
    assertEquals(4, rf.getTotalPopulation(
      List.of("alpha", "gamma")
    ));
  }
}
