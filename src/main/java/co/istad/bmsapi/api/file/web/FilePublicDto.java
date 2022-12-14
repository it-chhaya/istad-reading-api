package co.istad.bmsapi.api.file.web;

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
public class FilePublicDto {

    private Long id;
    private String uuid;
    private String name;
    private String uri;


}
