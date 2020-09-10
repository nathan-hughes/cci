package cci.ch1;

import cci.test.*;

public class StringRotation {

    public static boolean isRotationOf(String s1, String s2) {
        return isSubstring(s1 + s1, s2);
    }

    public static boolean isSubstring(String s, String sub) {
         return s.indexOf(sub) != -1;
    }

    public static void main(String... args) {
        TestRunner.runAllTests(StringRotation.class);
    }

    @Test
    public void noMatch() {
        Asserts.assertFalse(isRotationOf("aaaabbbbcc", "aaaabbbc")); 
    }

    @Test
    public void match() {
        Asserts.assertTrue(isRotationOf("erbottlewat", "waterbottle"));
    }
}


