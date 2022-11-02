package co.istad.bmsapi.api.file;

import org.mapstruct.Mapper;

import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.api.file.web.FilePublicDto;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File fromFileDto(FileDto fileDto);
    
    FileDto toFileDto(File file);

    FilePublicDto toFilePublicDto(File file);
    
}
