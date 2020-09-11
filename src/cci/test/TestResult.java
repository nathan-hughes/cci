package cci.test;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public final class TestResult {

    private final String testName;
    private final Optional<Throwable> throwable;

    public TestResult(String testName, Optional<Throwable> throwable) {
        if (testName == null) {
            throw new IllegalArgumentException("testName is required");
        }
        this.testName = testName;
        this.throwable = throwable;
    }

    public TestResult(Method testMethod, Optional<Throwable> throwable) {
        this(testMethod.toString(), throwable);
    }

    public TestResult(Method testMethod) {
        this(testMethod, Optional.empty());
    }

    public boolean isSuccessful() {
        return !throwable.isPresent();
    }

    public String toString() {
        if (isSuccessful()) return testName + " passed";
        return testName + " failed, caught " + formatThrowable(throwable.get());
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof TestResult)) return false;
        return toString().equals(other.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public static String stackTraceToString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    String formatThrowable(Throwable throwable) {
        Throwable t = throwable instanceof InvocationTargetException 
        ? throwable.getCause() : throwable;

        return stackTraceToString(t);
    }
}
