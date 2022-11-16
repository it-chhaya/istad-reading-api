package co.istad.bmsapi.api.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${api.base-url}")
    private String apiBaseUrl;


    @Override
    public void sendEmail(Mail<?> mail) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        var context = new Context();
        context.setVariable("additionalInfo", mail.getAdditionalInfo());
        context.setVariable("apiBaseUrl", apiBaseUrl);

        String html = templateEngine.process(mail.getTemplateName(), context);

        messageHelper.setTo(mail.getReceiver());
        messageHelper.setFrom(sender, appName);
        messageHelper.setText(html, true);
        messageHelper.setSubject(mail.getSubject());

        mailSender.send(message);
    }

}
