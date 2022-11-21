package co.istad.bmsapi.api.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class File {

    public File(Long id) {
        this.id = id;
    }

    private Long id;
    private String uuid;
    private String name;
    private String uri;
    private String extension;
    private Long size;
    private Boolean isEnabled;

}
