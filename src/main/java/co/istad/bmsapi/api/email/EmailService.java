package co.istad.bmsapi.api.email;

import co.istad.bmsapi.api.email.web.EmailDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    EmailDto sendEmail(EmailDto<?> emailDto) throws MessagingException, UnsupportedEncodingException;

}
