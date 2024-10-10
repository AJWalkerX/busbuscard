package com.ahmete.busbuscard;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        Long startTime = 1728560707682L;
        Long endTime = System.currentTimeMillis();
        Long passTimeInMillis = endTime - startTime;
        Long passTimeInSecond = passTimeInMillis/1000;
        Long passTimeInMinutes = passTimeInSecond/60;
        System.out.println("Pass time: " + passTimeInMillis + " millis");
        System.out.println("Pass time: " + passTimeInSecond + " seconds");
        System.out.println("Pass time: " + passTimeInMinutes + " minutes");

        LocalDateTime localDateTime = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);
    }
}
