package com.cybertek.library.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandomString {
    public static String generateRandomString(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890";
        String name = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        name = temp.substring(0, temp.length() - 9)+"";
        return name;
    }
}
