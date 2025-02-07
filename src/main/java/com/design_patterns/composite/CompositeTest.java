package com.design_patterns.composite;

import java.util.*;

interface ValueContainer extends Iterable<Integer> {}

class SingleValue implements ValueContainer
{
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value)
    {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(value).iterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{

    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c)
    {
        super(c);
    }

    public MyList(){}

    public int sum()
    {
        int sum = 0;
        for(ValueContainer valueContainer : this){
            for(Integer value : valueContainer){
                sum += value;
            }
        }

        return sum;
    }
}
public class CompositeTest {
    public static void main(String[] args) {
        SingleValue singleValue = new SingleValue(1);
        ManyValues manyValues = new ManyValues();
        manyValues.add(1);
        manyValues.add(10);

        MyList myList = new MyList();
        myList.add(singleValue);
        myList.add(manyValues);

        System.out.println(myList.sum());

    }
}
