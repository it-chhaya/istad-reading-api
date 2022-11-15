package co.istad.bmsapi.api.user;

import java.util.List;

import co.istad.bmsapi.api.file.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {
    
    private Long id;
    private String username;
    private String email;
    private String password;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private File profile;
    private String biography;
    private Boolean isEnabled;

    private List<Role> roles;

}
