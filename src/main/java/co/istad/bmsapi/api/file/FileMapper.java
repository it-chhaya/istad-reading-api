package co.istad.bmsapi.api.file;

import org.mapstruct.Mapper;

import co.istad.bmsapi.api.file.web.FileDto;

@Mapper(componentModel = "spring")
public interface FileMapper {
    
    FileDto fromModel(File file);

    File toModel(FileDto fileDto);

}
