package com.github.spranshu1.csvtojson.util;

public class CommonUtil {

    public static boolean isNumeric(final String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);
    }


}
