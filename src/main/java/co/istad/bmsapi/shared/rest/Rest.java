package co.istad.bmsapi.shared.rest;

import java.sql.Timestamp;

import co.istad.bmsapi.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rest<T> {

    public Rest() {
        this.timestamp = DateTimeUtils.getTS();
    }
    
    private Boolean status;
    private Integer code;
    private String message;
    private Timestamp timestamp;
    private T data;

}
