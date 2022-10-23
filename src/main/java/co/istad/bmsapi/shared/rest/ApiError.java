package co.istad.bmsapi.shared.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    
    private String field;
    private String message;

}
