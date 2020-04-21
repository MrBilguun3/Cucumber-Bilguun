package com.cybertek.library.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dateTimeFormatUtils {
        public static String getCurrentDate(String datePattern){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
            LocalDate localDate = LocalDate.now();
            return dtf.format(localDate);
        }
    }


