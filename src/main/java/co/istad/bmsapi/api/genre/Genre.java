package co.istad.bmsapi.api.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {

    public Genre(Integer id) {
        this.id = id;
    }
    
    private Integer id;
    private String title;
    private String description;
    private Integer icon;
    private Boolean isEnabled;

}
