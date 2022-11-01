package co.istad.bmsapi.api.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileDto {

    private Long id;
    private String uuid;
    private String extension;
    private Double size;
    private Boolean isEnabled;

}
