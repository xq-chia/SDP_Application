package com.assignment.sdp_application;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import java.util.Calendar;


public class sampleTest {
    public static void main(String[] args) {
        Calendar months = Calendar.getInstance(Locale.getDefault());
        for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {
            months.set(Calendar.MONTH, i);
            String c2 = months.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault());
            System.out.println(c2);
        }
    }
}
