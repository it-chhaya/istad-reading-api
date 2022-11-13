package co.istad.bmsapi.shared.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestError<T> {

    private Boolean status;
    private Integer code;
    private String message;
    private Timestamp timestamp;
    private T errors;

}
