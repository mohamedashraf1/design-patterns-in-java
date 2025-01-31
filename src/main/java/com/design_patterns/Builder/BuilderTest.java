package com.design_patterns.Builder;

import java.util.ArrayList;
import java.util.List;

class MyField{
  public String name;
  public String type;
  public MyField(String name, String type){
    this.name = name;
    this.type = type;
  }
}

class Code{
  public String className;
  public List<MyField> fields = new ArrayList<>();
}


class MyCodeBuilder
{
  private Code code = new Code();

  public MyCodeBuilder(String className)
  {
    this.code.className = className;
  }

  public MyCodeBuilder addField(String name, String type)
  {
    code.fields.add(new MyField(name, type));
    return this;
  }

  @Override
  public String toString()
  {
    String line = System.lineSeparator();
    StringBuilder sp = new StringBuilder();
    sp.append("public class ")
            .append(code.className).append(line)
            .append("{").append(line);

    for(MyField field : code.fields){
      sp.append("  public ").append(field.type).append(" ").append(field.name).append(";").append(line);
    }
    sp.append("}");
    return sp.toString();
  }
}

public class BuilderTest
{

  public static void main(String[] args)
  {
    MyCodeBuilder cb = new MyCodeBuilder("Person")
            .addField("name", "String")
            .addField("age", "int");
    System.out.println(cb);
  }
}
