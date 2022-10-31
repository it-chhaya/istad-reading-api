package co.istad.bmsapi.api.user;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class Role implements GrantedAuthority {
    
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        // All Roles must prefix with 'ROLE_'
        // Example -> Admin -> 'ROLE_ADMIN'
        return "ROLE_" + name;
    }

}
