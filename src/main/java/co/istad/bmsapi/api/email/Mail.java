package co.istad.bmsapi.api.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Mail<T> {

    private String receiver;
    private String subject;
    private String templateName;
    private T additionalInfo;

}
