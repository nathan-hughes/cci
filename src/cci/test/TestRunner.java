package cci.test;

import java.util.*;
import java.lang.reflect.*;

public interface TestRunner {

    static void runAllTests(Class<?> testClass) {
        testMethods(testClass)
        .stream()
        .map(method -> runTest(newInstance(testClass), method))
        .forEach(System.out::println);
    }

    static TestResult runTest(Object test, Method method) {
        try {
            method.invoke(test);
            return new TestResult(method);
        } catch (Throwable throwable) {
            return new TestResult(method, Optional.of(throwable));
        }
    }

    static List<Method> testMethods(Class<?> testClass) {
        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getDeclaredAnnotation(Test.class) != null) {
                methods.add(method);
            }
        }
        return methods;
    }

    static Object newInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException 
        | NoSuchMethodException 
        | InstantiationException 
        | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}