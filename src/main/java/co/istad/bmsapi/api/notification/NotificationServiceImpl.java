package co.istad.bmsapi.api.notification;

import co.istad.bmsapi.api.notification.web.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${one-signal.base-url}")
    private String oneSignalBaseUrl;

    @Value("${one-signal.app-id}")
    private String oneSignalAppId;

    @Value("${one-signal.rest-api-key}")
    private String oneSignalRestApiKey;

    @Override
    public NotificationDto push(NotificationDto notificationDto) throws IOException {

        URL url = new URL(oneSignalBaseUrl + "notifications");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Authorization", "Basic " + oneSignalRestApiKey);
        con.setRequestMethod("POST");

        String strJsonBody = "{"
                + "\"app_id\": \"" + oneSignalAppId + "\","
                + "\"included_segments\": [\"All\"],"
                + "\"url\": \"" + notificationDto.getDeepLink() + "\","
                + "\"big_picture\": \"" + notificationDto.getBannerUri() + "\","
                + "\"contents\": {\"en\": \"" + notificationDto.getDescription() + "\"},"
                + "\"headings\": {\"en\": \"" + notificationDto.getTitle() + "\"}"
                + "}";

        byte[] sendBytes = strJsonBody.getBytes(StandardCharsets.UTF_8);
        con.setFixedLengthStreamingMode(sendBytes.length);

        OutputStream outputStream = con.getOutputStream();
        outputStream.write(sendBytes);

        int httpResponseCode = con.getResponseCode();

        NotificationHelper.mountResponseRequest(con, httpResponseCode);

        return notificationDto;
    }

}
