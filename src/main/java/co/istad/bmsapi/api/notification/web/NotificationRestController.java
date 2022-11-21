package co.istad.bmsapi.api.notification.web;

import co.istad.bmsapi.api.notification.NotificationServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final NotificationServiceImpl notificationService;

    @PostMapping
    ResponseEntity<?> doNotificationPush(@Valid @RequestBody NotificationDto notificationDto) throws IOException {

        notificationDto = notificationService.push(notificationDto);

        Rest<Object> rest = Rest.ok()
                .setMessage("Notification has been pushed successfully.")
                .setData(notificationDto);

        return ResponseEntity.ok(rest);
    }

}
