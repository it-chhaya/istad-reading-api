package co.istad.bmsapi.api.user.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    
    private String email;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private String biography;

    // private List<Role> roles;

}
