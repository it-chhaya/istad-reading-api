package co.istad.bmsapi.api.email;

import co.istad.bmsapi.api.email.web.ConfirmationDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendEmail(Mail<?> mail) throws MessagingException, UnsupportedEncodingException;

}
