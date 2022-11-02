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

    private Long id;
    private String uuid;
    private String extension;
    private Long size;
    private Boolean isEnabled;

}
