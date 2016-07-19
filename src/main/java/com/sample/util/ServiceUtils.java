package com.sample.util;



import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.UUID;

public class ServiceUtils {
    public static String createAuthToken(Long userId){
        String token = UUID.randomUUID().toString().toUpperCase()
                + "|" + userId + "|"
                + LocalDateTime.now().get(ChronoField.MILLI_OF_SECOND);
        return token;
    }
}
