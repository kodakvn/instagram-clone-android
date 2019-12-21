package com.example.instagramclone.utils;

public class StringManipulation {
    public static String expandUserName(String username) {
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username) {
        return username.replace(" ", ".");
    }
}
