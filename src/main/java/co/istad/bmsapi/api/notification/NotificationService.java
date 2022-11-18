package co.istad.bmsapi.api.notification;

import co.istad.bmsapi.api.notification.web.NotificationDto;

import java.io.IOException;
import java.net.MalformedURLException;

public interface NotificationService {

    /**
     * Push notification to all OneSignal subscribers
     * @param notificationDto is the data of notification
     * @return NotificationDto
     * @throws IOException
     */
    NotificationDto push(NotificationDto notificationDto) throws IOException;

}
