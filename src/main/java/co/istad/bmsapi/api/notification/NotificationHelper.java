package co.istad.bmsapi.api.notification;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NotificationHelper {

    public static String mountResponseRequest(HttpURLConnection con, int httpResponse) throws IOException {

        String jsonResponse;
        Scanner scanner;

        if (httpResponse >= HttpURLConnection.HTTP_OK
                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
            scanner = new Scanner(con.getInputStream(), StandardCharsets.UTF_8);
        } else {
            scanner = new Scanner(con.getErrorStream(), StandardCharsets.UTF_8);
        }
        jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        scanner.close();

        return jsonResponse;
    }

}
