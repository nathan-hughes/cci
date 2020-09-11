package cci.test;

public class AssertFailedException extends RuntimeException {

    private Object expected;
    private Object actual;

    public AssertFailedException(Object actual, Object expected, String message) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    public Object getExpected() { return expected; }

    public Object getActual() { return actual; }
}