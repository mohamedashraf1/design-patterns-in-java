package com.design_patterns.singleton;

import java.util.Objects;
import java.util.function.Supplier;

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        Object first = func.get();
        Object second = func.get();

        return Objects.equals(first, second);
    }
}