package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class utils {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getDateTime() {
        return LocalDateTime.now().toString();
    }

    public static String getDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

}
