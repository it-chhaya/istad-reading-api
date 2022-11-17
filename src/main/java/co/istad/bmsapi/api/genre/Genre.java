package co.istad.bmsapi.api.genre;

import co.istad.bmsapi.api.file.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {
    
    private Integer id;
    private String title;
    private String description;
    private File icon;
    private Boolean isEnabled;

}
