package cci.test;

public interface Asserts {

    static void assertEq(boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertFailedException(actual, expected, formatAssertsOneLine(actual, expected));
        }
    }

    static void assertTrue(boolean actual) {
        assertEq(actual, true);
    }

    static void assertFalse(boolean actual) {
        assertEq(actual, false);
    }

    static void assertEq(Object actual, Object expected) {
        if (actual == null || expected == null) {
            if (actual == expected) {
                return;
            }
            else {
                throw new AssertFailedException(actual, expected, formatAssertsOneLine(actual, expected));                
            }
        }
        if (!expected.equals(actual)) {
            throw new AssertFailedException(actual, expected, formatAssertsMultiLine(actual, expected));
        }      
    }

    static void assertNull(Object actual) {
        assertEq(actual, null);
    }

    static String formatAssertsOneLine(Object actual, Object expected) {
        return String.format("Assertion failed: expected [%s] but found [%s]", expected, actual);
    }

    static String formatAssertsMultiLine(Object actual, Object expected) {
        return String.format("Assertion failed: %nexpected [%s]%nfound    [%s]", expected, actual);
    }
}