package co.istad.bmsapi.shared.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.istad.bmsapi.shared.rest.ApiError;
import co.istad.bmsapi.shared.rest.RestError;
import co.istad.bmsapi.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestException {
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {
        log.info("Error Validation");

        List<ApiError> apiErrors = new ArrayList<>();
        
        for (FieldError error : e.getFieldErrors()) {
            ApiError apiError = new ApiError(error.getField(), error.getDefaultMessage());
            apiErrors.add(apiError);
        }

        RestError rest = new RestError();
        rest.setStatus(false);
        rest.setCode(HttpStatus.BAD_REQUEST.value());
        rest.setMessage(HttpStatus.BAD_REQUEST.name());
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setErrors(apiErrors);

        return new ResponseEntity<>(rest, HttpStatus.BAD_REQUEST);

    }

}
