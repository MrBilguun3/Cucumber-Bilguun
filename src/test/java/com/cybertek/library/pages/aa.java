package com.cybertek.library.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class aa {
    public static void main(String[] args) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = date.format(new Date());
        System.out.println(formatted);
    }
}
