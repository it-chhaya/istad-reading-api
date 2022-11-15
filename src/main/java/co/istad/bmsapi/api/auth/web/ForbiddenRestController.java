package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.shared.rest.Rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForbiddenRestController {

    @GetMapping("/forbidden")
    ResponseEntity<?> forbidden() {
        return new ResponseEntity<>(Rest.forbidden(), HttpStatus.FORBIDDEN);
    }

}
