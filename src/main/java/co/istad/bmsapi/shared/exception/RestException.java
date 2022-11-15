package co.istad.bmsapi.shared.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

import co.istad.bmsapi.shared.rest.ValidatedError;
import co.istad.bmsapi.shared.rest.RestError;
import co.istad.bmsapi.utils.DateTimeUtils;

@RestControllerAdvice
public class RestException {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleAccessDenied(RuntimeException e) {

        System.out.println("access failed");
        var rest = new RestError<String>();
        rest.setStatus(false);
        rest.setCode(HttpStatus.FORBIDDEN.value());
        rest.setMessage("You cannot access this resources!");
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setError(e.getMessage());

        return new ResponseEntity<>(rest, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException e) {

        var rest = new RestError<String>();
        rest.setStatus(false);
        rest.setCode(HttpStatus.UNAUTHORIZED.value());
        rest.setMessage("Log in failed!");
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setError(e.getMessage());

        return new ResponseEntity<>(rest, HttpStatus.UNAUTHORIZED);

    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {

        List<ValidatedError> validatedErrors = new ArrayList<>();
        
        for (FieldError error : e.getFieldErrors()) {
            ValidatedError validatedError = new ValidatedError(error.getField(), error.getDefaultMessage());
            validatedErrors.add(validatedError);
        }

        var rest = new RestError<List<ValidatedError>>();
        rest.setStatus(false);
        rest.setCode(HttpStatus.BAD_REQUEST.value());
        rest.setMessage(HttpStatus.BAD_REQUEST.name());
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setError(validatedErrors);

        return new ResponseEntity<>(rest, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingServletRequestPart(MissingServletRequestPartException e) {

        var rest = new RestError<String>();
        rest.setStatus(false);
        rest.setCode(HttpStatus.NOT_FOUND.value());
        rest.setMessage(HttpStatus.NOT_FOUND.name());
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setError(e.getMessage());

        return new ResponseEntity<>(rest, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<?> handleService(ResponseStatusException e) {

        var rest = new RestError<String>();
        rest.setStatus(false);
        rest.setCode(e.getStatus().value());
        rest.setMessage(e.getReason());
        rest.setTimestamp(DateTimeUtils.getTS());
        rest.setError(e.getCause().getMessage());

        return new ResponseEntity<>(rest, HttpStatus.NOT_FOUND);

    }


}
