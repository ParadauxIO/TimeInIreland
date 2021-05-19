package io.paradaux.timeinireland;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeInIreland {

    private static final String IMAGE_CAPTION = "It's currently %s in Ireland.";

    public static void main(String[] args) throws IGLoginException {
        // What hour it is in Dublin (Ireland)
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Dublin"));


        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        IGClient client = IGClient.builder()
                .username(System.getenv("instagram-username"))
                .password(System.getenv("instagram-password"))
                .login();

        client.actions().timeline()
                .uploadPhoto(getResourceAsFile("/times/" + hour + ".jpg"), String.format(IMAGE_CAPTION, getTimeString(hour)))
                .thenAccept(response -> System.out.println("Successfully posted for " + getTimeString(hour)));
    }

    /**
     * Creates a String from a 24-hour clock. 0 returns 12am, 1 returns 1am, 16 returns 4pm, etc.
     *
     * */
    @NotNull
    public static String getTimeString(int hour) {
        if (hour == 0) return "12am";
        return (hour < 12) ? hour + "am" : (hour-12) + "pm";
    }

    /**
     * Creates a temporary java.io.File from a JAR Resource. It is deleted after the JVM exits.
     * @param resourcePath The path within the JAR to the requested resource.
     * */
    @Nullable
    public static File getResourceAsFile(String resourcePath) {
        try {
            InputStream in = TimeInIreland.class.getResourceAsStream(resourcePath);
            if (in == null) {
                System.out.println("a");
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
