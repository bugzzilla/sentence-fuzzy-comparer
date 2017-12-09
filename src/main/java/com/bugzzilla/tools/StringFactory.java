package com.bugzzilla.tools;

public class StringFactory {

    public static boolean isNullOrWhiteSpace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String substring(String value, int startIndex, int length) {
        try {
            return new String(value.getBytes(), startIndex, length);
        } catch (Exception e) {
            return "";
        }
    }
}
