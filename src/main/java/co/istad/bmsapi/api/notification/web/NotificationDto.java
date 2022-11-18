package co.istad.bmsapi.api.notification.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotificationDto {

    @NotBlank
    private String title;

    private String bannerUri;

    @NotBlank
    private String deepLink;

    private String description;

}
