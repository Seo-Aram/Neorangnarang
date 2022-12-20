package com.app.rang.project.util;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Util {

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    public static String convertLongTimestampToString(long timestamp) {
        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp/1000),
                TimeZone.getDefault().toZoneId());
        return DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss").format(localDate);
    }
}
