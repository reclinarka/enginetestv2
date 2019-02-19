package de.reclinarka.util;

public class StringTrimmer {
    public static String trim(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}