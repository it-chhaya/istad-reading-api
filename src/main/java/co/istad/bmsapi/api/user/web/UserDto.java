package co.istad.bmsapi.api.user.web;

import co.istad.bmsapi.api.file.web.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username;
    private String email;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private String biography;
    private FileDto profile;

}
